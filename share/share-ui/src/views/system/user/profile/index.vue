<template>
  <div class="user-center-container">
    <!-- 主内容 -->
    <div class="profile-card">
      <!-- 头像区域 -->
      <div class="avatar-section">
        <el-avatar
          :size="120"
          :src="user.avatar ? 'http://localhost:8080111' + user.avatar : ''"
          class="user-avatar"
        >
          {{ user.user_name ? user.user_name.charAt(0).toUpperCase() : 'U' }}
        </el-avatar>
      </div>

      <!-- 用户信息 -->
      <div class="info-section">
        <!-- 用户类型标签 -->
        <div class="user-type">
          <el-tag
            :type="userTypeStyle.type"
            effect="plain"
            :class="userTypeStyle.class"
            style="display: flex; align-items: center; justify-content: center;"
          >
            {{ userTypeText }}
          </el-tag>
        </div>

        <!-- 信息列表 -->
        <div class="info-list">
          <div class="info-item">
            <span class="info-label">用户名</span>
            <span class="info-value">{{ user.userName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">手机号</span>
            <span class="info-value">{{ user.phonenumber || '未绑定' }}
              <i class="el-icon-edit-outline edit-icon" @click="showPhoneDialog"></i>
            </span>
          </div>

        </div>

        <!-- 密码修改入口 -->
        <div class="info-item">
          <span class="info-label">账户安全</span>
          <el-button
            type="text"
            class="security-button"
            @click="showPasswordDialog">
            更改密码
          </el-button>
        </div>


        <!-- 手机号编辑对话框 -->
        <el-dialog
          :visible.sync="showPhoneEditor"
          title="修改手机号"
          width="30%"
          class="apple-dialog"
          center>
          <el-form
            :model="phoneForm"
            :rules="phoneRules"
            ref="phoneForm"
            @submit.native.prevent>
            <div class="dialog-content">
              <div class="input-group">
                <label class="input-label">手机号码</label>
                <el-form-item prop="phonenumber">
                  <el-input
                    v-model="phoneForm.phonenumber"
                    placeholder="请输入11位手机号码"
                    class="apple-input"
                    maxlength="11"
                    clearable>
                    <i slot="prefix" class="el-icon-phone-outline input-icon"></i>
                  </el-input>
                </el-form-item>
              </div>
            </div>
          </el-form>
          <span slot="footer" class="dialog-footer">
      <el-button class="apple-button" @click="showPhoneEditor = false">取消</el-button>
      <el-button
        class="apple-button primary-button"
        type="primary"
        @click="savePhone">
        更新号码
      </el-button>
    </span>
        </el-dialog>

        <!-- 密码修改对话框 -->
        <el-dialog
          :visible.sync="showPasswordEditor"
          title="账户安全"
          width="30%"
          class="apple-dialog"
          center>
          <el-form
            :model="pass"
            :rules="passwordRules"
            ref="passwordForm"
            @submit.native.prevent>
            <div class="dialog-content">
              <div class="input-group">
                <label class="input-label">当前密码</label>
                <el-form-item prop="oldPassword">
                  <el-input
                    v-model="pass.oldPassword"
                    placeholder="输入当前使用的密码"
                    type="password"
                    class="apple-input"
                    clearable>
                    <i slot="prefix" class="el-icon-lock input-icon"></i>
                  </el-input>
                </el-form-item>
              </div>
              <div class="input-group" style="margin-top: 20px;">
                <label class="input-label">新密码</label>
                <el-form-item prop="newPassword">
                  <el-input
                    v-model="pass.newPassword"
                    placeholder="至少8位字符，包含字母和数字"
                    type="password"
                    class="apple-input"
                    clearable>
                    <i slot="prefix" class="el-icon-unlock input-icon"></i>
                  </el-input>
                </el-form-item>
              </div>
            </div>
          </el-form>
          <span slot="footer" class="dialog-footer">
      <el-button class="apple-button" @click="showPasswordEditor = false">取消</el-button>
      <el-button
        class="apple-button primary-button"
        type="primary"
        @click="savePassword">
        更改密码
      </el-button>
    </span>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
import {getUserProfile, updateUserProfile, updateUserPwd} from "@/api/system/user";
import {addAddress, listAddress, updateAddress} from "@/api/system/address";


export default {
  name: 'User',
  data() {

    const validatePhone = (rule, value, callback) => {
      if (!/^1[3-9]\d{9}$/.test(value)) {
        callback(new Error("请输入有效的手机号码"));
      } else {
        callback();
      }
    };

    const validatePassword = (rule, value, callback) => {
      const pattern = /^[^<>"'|\\]+$/;
      if (!pattern.test(value)) {
        callback(new Error("不能包含非法字符：< > \" ' \\\ |"));
      } else {
        callback();
      }
    };


    return {
      loading: true,
      user: {},
      queryParams: {},
      addressList: [],
      form: {},
      nickName: '',

      showAddressEditor: false,
      showPhoneEditor: false,
      showPasswordEditor: false,
      tempAddress: '',
      tempPhone: '',
      oldPassword: '',
      newPassword: '',
      editingAddressIndex: '',


      phoneForm: {
        phonenumber: ""
      },

      // 校验规则
      phoneRules: {
        phonenumber: [
          { required: true, message: "请输入手机号码", trigger: "blur" },
          { validator: validatePhone, trigger: "blur" }
        ]
      },

      passwordRules: {
        oldPassword: [
          { required: true, message: "请输入当前密码", trigger: "blur" }
        ],
        newPassword: [
          { required: true, message: "请输入新密码", trigger: "blur" },
          { min: 5, max: 20, message: "长度必须介于 5 和 20 之间", trigger: "blur" },
          { validator: validatePassword, trigger: "blur" }
        ]
      },



      pass: {
        oldPassword: undefined,
        newPassword: undefined
      }
    };
  },
  computed: {
    userTypeText() {
      switch (this.user.roles[0].roleId) {
        case 2:
          return '普通用户';
        case 100:
          return '认证商家';
        case 1:
          return '系统管理员';
        default:
          return '未知类型';
      }
    },
    userTypeStyle() {
      switch (this.user.roles[0].roleId) {
        case 2:
          return {type: 'info', class: 'standard-user'};
        case 100:
          return {type: '', class: 'business-user'};
        case 1:
          return {type: 'danger', class: 'admin-user'};
        default:
          return {type: 'warning', class: 'unknown-user'};
      }
    }
  },
  async created() {
    await this.getUserP();
    this.getAddress();
  },
  methods: {
    async getUserP() {
      const response = await getUserProfile();
      this.user = response.data;
    },
    getAddress() {
      this.queryParams.userId = this.$route.query.userId;
      listAddress(this.queryParams).then(response => {
        this.addressList = response.rows;
      });
    },

    showPhoneDialog() {
      this.phoneForm.phonenumber = this.user.phonenumber;
      this.showPhoneEditor = true;
      this.$nextTick(() => {
        this.$refs.phoneForm.clearValidate();
      });

    },
    showPasswordDialog() {
      this.showPasswordEditor = true;
    },

    async savePhone() {
      this.$refs.phoneForm.validate(valid => {
        if (valid) {
          this.form.phonenumber = this.phoneForm.phonenumber;
          updateUserProfile(this.form).then(response => {
            this.$modal.msgSuccess("号码修改成功");
            this.showPhoneEditor = false;
            this.getUserP();
          });
        }
      });
    },

    async savePassword() {
      this.$refs.passwordForm.validate(valid => {
        if (valid) {
          updateUserPwd(this.pass.oldPassword, this.pass.newPassword).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.showPasswordEditor = false;
          });
        }
      });
    },


  }
};
</script>

<style scoped>
.user-center-container {
  max-width: 680px;
  margin: 40px auto;
  padding: 0 20px;
}

.profile-card {
  background: #ffffff;
  border-radius: 18px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
  padding: 40px;
}

/* 原有样式保持不变... */

/* 对话框美化 */
.apple-dialog {
  border-radius: 14px !important;
}

.apple-dialog >>> .el-dialog {
  border-radius: 14px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
}

.apple-dialog >>> .el-dialog__header {
  padding: 24px 24px 16px;
  border-bottom: 0.5px solid rgba(0, 0, 0, 0.1);
}

.apple-dialog >>> .el-dialog__title {
  font-size: 18px;
  font-weight: 600;
  color: #1d1d1f;
  letter-spacing: 0.2px;
}

.apple-dialog >>> .el-dialog__body {
  padding: 20px 24px;
}

.dialog-content {
  padding: 0 8px;
}

.input-group {
  margin-bottom: 20px;
}

.input-label {
  display: block;
  color: #86868b;
  font-size: 13px;
  margin-bottom: 8px;
  letter-spacing: 0.3px;
}

.apple-input >>> .el-input__inner {
  height: 44px;
  line-height: 44px;
  padding-left: 40px;
  font-size: 15px;
  border-radius: 12px;
  border: 1px solid #d2d2d7;
  transition: all 0.2s ease;
}

.apple-input >>> .el-input__inner:focus {
  border-color: #0071e3;
  box-shadow: 0 0 0 3px rgba(0, 113, 227, 0.1);
}

.input-icon {
  font-size: 20px;
  color: #86868b;
  margin: 12px 0 0 10px;
}

.apple-button {
  border-radius: 20px;
  padding: 10px 24px;
  font-size: 14px;
  font-weight: 500;
  letter-spacing: 0.5px;
  transition: all 0.2s ease;
}

.primary-button {
  background: #0071e3;
  border-color: #0071e3;
}

.primary-button:hover {
  background: #0077ed;
  transform: translateY(-1px);
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  padding: 16px 24px;
  border-top: 0.5px solid rgba(0, 0, 0, 0.1);
}

@media (max-width: 768px) {
  .apple-dialog {
    width: 80% !important;
  }

  .apple-dialog >>> .el-dialog {
    margin-top: 15vh !important;
  }

  .apple-input >>> .el-input__inner {
    height: 40px;
    line-height: 40px;
  }
}


.user-center-container {
  max-width: 680px;
  margin: 40px auto;
  padding: 0 20px;
}

.profile-card {
  background: #ffffff;
  border-radius: 18px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
  padding: 40px;
}

.avatar-section {
  text-align: center;
  margin-bottom: 32px;
}

.user-avatar {
  border: 2px solid #f5f5f7;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  font-size: 48px;
  font-weight: 500;
  background: linear-gradient(135deg, #f8f8f8 0%, #f0f0f0 100%);
}

.info-section {
  text-align: center;
}

.user-type {
  margin-bottom: 24px;
}

.info-list {
  border-top: 1px solid rgba(0, 0, 0, 0.05);
  padding-top: 24px;
}

.info-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 14px 0;
  border-bottom: 1px solid rgba(0, 0, 0, 0.03);
}

.info-value {
  color: #1d1d1f;
  font-size: 15px;
  font-weight: 500;
}

.info-label {
  color: #86868b;
  font-size: 15px;
  letter-spacing: 0.3px;
}

/* 用户类型标签样式 */
.el-tag {
  border-radius: 10px;
  padding: 8px 16px;
  font-size: 14px;
  letter-spacing: 0.5px;
}

.standard-user {
  background: rgba(0, 113, 227, 0.1);
  color: #0071e3;
  border-color: rgba(0, 113, 227, 0.2);
}

.business-user {
  background: rgba(52, 199, 89, 0.1);
  color: #34c759;
  border-color: rgba(52, 199, 89, 0.2);
}

.admin-user {
  background: rgba(255, 59, 48, 0.1);
  color: #ff3b30;
  border-color: rgba(255, 59, 48, 0.2);
}

.unknown-user {
  background: rgba(174, 174, 178, 0.1);
  color: #aeaeae;
}

.loading-container {
  display: flex;
  justify-content: center;
  padding: 60px 0;
}

.el-icon-loading {
  font-size: 32px;
  color: #0071e3;
  animation: rotating 2s linear infinite;
}

@keyframes rotating {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

@media (max-width: 640px) {
  .profile-card {
    padding: 30px 20px;
  }

  .user-avatar {
    width: 100px;
    height: 100px;
    font-size: 36px;
  }
}


.info-edit {
  display: flex;
  align-items: center;
  gap: 8px;
}

.edit-icon {
  color: #0071e3;
  font-size: 18px;
  cursor: pointer;
  transition: opacity 0.3s;
}

.edit-icon:hover {
  opacity: 0.8;
}

.security-button {
  color: #0071e3;
  font-size: 15px;
  padding: 4px 8px;
}

/* 苹果风格对话框 */
.apple-dialog {
  border-radius: 12px !important;
}

.apple-dialog .el-dialog__header {
  border-bottom: 1px solid #e0e0e0;
  padding: 16px 24px;
}

.apple-dialog .el-dialog__title {
  font-weight: 500;
  color: #1d1d1f;
}

.apple-input {
  border-radius: 8px;
  border: 1px solid #d2d2d7;
}

.apple-input:focus {
  border-color: #0071e3;
}

.mt-15 {
  margin-top: 15px;
}


</style>
