<template>
  <div>
    <div>
      <div class="div1" style=" display: flex; height: 60px;line-height:60px;">
        <div style="width: 300px;display:flex;padding-left: 60px">
          <div style="color: #001528">SHARE</div>
          <div style="flex: 1 ;">共享租赁平台</div>
        </div>

        <div style="flex: 1">
          <el-menu class="el-menu-demo" mode="horizontal">
            <el-menu-item index="1" @click="goHome">首页</el-menu-item>
            <el-menu-item index="2" @click="goToPhonePage">手机</el-menu-item>
            <el-menu-item index="3" @click="goComputerPage">电脑</el-menu-item>
            <el-menu-item index="4" @click="goCameraPage">相机</el-menu-item>
            <el-menu-item index="5" @click="goPlanePage">无人机</el-menu-item>
          </el-menu>
        </div>


        <div style="padding-right: 20px">
          <!-- 使用 el-input 作为搜索框 -->
          <el-input

            v-model="searchKeyword"
            placeholder="请输入搜索关键词"
            @keyup.enter="handleSearch"
            style="width: 250px ;padding-right: 20px"
          >
            <!-- 搜索按钮 -->
            <template #suffix>
              <el-button icon="el-icon-search" @click="handleSearch"></el-button>
            </template>
          </el-input>
        </div>


        <div style="width: 200px;color:#FFFFFF;padding-right: 100px;">
          <!-- 根据 user.username 是否存在来控制按钮显示 -->
          <div v-if="!user.userName" style="text-align: right;padding-right: 30px">
            <el-button @click="$router.push('/login')">登录</el-button>
            <el-button @click="$router.push('/register')">注册</el-button>
          </div>
          <div v-else>
            <el-dropdown style="width: 150px;cursor: pointer;text-align: right;padding-left: -50px">
              <div style="white-space: nowrap;">
                <!-- 显示用户昵称 -->
                <span style="padding-left: -100px">{{ time }}，{{ user.userName }}</span>
                <i class="el-icon-caret-bottom"/>
              </div>
              <!-- 下拉菜单 -->
              <el-dropdown-menu>
                <el-dropdown-item @click.native="goUser">
                  <span>个人信息</span></el-dropdown-item>
                <el-dropdown-item @click.native="goOrder">
                  <span>我的订单</span></el-dropdown-item>
                <el-dropdown-item divided @click.native="logout">
                  <span>退出登录</span></el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </div>


      <router-view/>
    </div>

  </div>
</template>


<script>


import {getUserProfile} from "@/api/system/user";


export default {
  name: 'front',

  beforeCreate() {
    document.querySelector('body').setAttribute("style", "background-color: rgb(245,245,247)");
  },

  data() {
    return {

      time: this.getTime(),
      user: {},
      searchKeyword: "",
      activeTab: "userinfo"
    }
  },
  created() {
    this.getUser();
  },

  methods: {
    goUser() {
      this.$router.push({
        path: '/front/user',
        query: {
          userId: this.user.userId
        }
      });
    },

    goOrder() {
      this.$router.push({
        path: '/front/order',
        query: {
          userId: this.user.userId
        }
      });
    },


    handleSearch() {
      const keyword = this.searchKeyword.trim();
      this.searchKeyword = "";
      if (keyword) {
        this.$router.push({
          path: '/front/search',
          query: {
            name: keyword
          }
        })
          .catch(error => {
            console.error('路由跳转失败:', error);
            this.$message.error('搜索跳转失败，请稍后再试');
          });
      } else {
        this.$message.warning('请输入搜索关键词');
      }
    },


    goToPhonePage() {
      this.$router.push('/front/phone');
    },
    goHome() {
      this.$router.push('/front/home');
    },
    goComputerPage() {
      this.$router.push('/front/computer');
    },
    goCameraPage() {
      this.$router.push('/front/camera');
    },
    goPlanePage() {
      this.$router.push('/front/plane');
    },
    async logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          location.href = '/index';
        })
      }).catch(() => {
      });
    },

    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
      });
    },

    getTime() {
      let now = new Date().getHours()
      if (now < 6) {
        return "凌晨好"
      } else if (now < 9) {
        return "早上好"
      } else if (now < 12) {
        return "上午好"
      } else if (now < 14) {
        return "中午好"
      } else if (now < 17) {
        return "下午好"
      } else if (now < 19) {
        return "傍晚好"
      } else {
        return "晚上好"
      }
    }
  }
}

</script>


<style scoped lang="scss">

.div1 {
  background-color: rgb(255, 255, 255);
}

</style>
