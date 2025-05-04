<template>
  <div>
    <el-container>
      <el-row style="padding-left: 150px ;padding-top: 100px ;display: flex">


        <div>
          <div style="width: 500px; height: 500px;">
            <el-card :body-style="{ padding: '20px' } " shadow="hover" style="padding-bottom: 100px">
              <div class="image" @mousemove="handleMouseMove" @mouseleave="handleMouseLeave">
                <img :src="'http://localhost:8080' +goodPhoneList[0].img" alt="">
              </div>
              <div style="padding: 14px;">
                <span class="blackWhite-text" style="font-size: 18px ;font-weight: 600">{{
                    goodPhoneList[0].name
                  }}</span>
                <div class="bottom clearfix">
                  <time class="time">RMB {{ goodPhoneList[0].price }}/天</time>
                </div>
              </div>
              <div style=" color: #5a5e66; padding-left: 300px">
                ————来自{{ goodPhoneList[0].busName }}
              </div>
            </el-card>
          </div>


          <div style="height: 200px ;color: #f5f5f7"></div>
          <div>
            <div>
              <div v-for="(order, index) in evaluteList" :key="order.id" class="order-container">
                <!-- 显示用户 ID -->
                <div>用户 ID: {{ order.userId }}</div>
                <!-- 显示评价内容 -->
                <div>评价内容: {{ order.content }}</div>
                <!-- 显示评价状态，根据状态添加不同的类名 -->
                <div :class="getStatusClass(order.status)">评价状态: {{ getStatusText(order.status) }}</div>
              </div>
            </div>
          </div>
        </div>


        <div style="color: #FFFFFF ;width: 800px; height: 1800px">
          <el-card shadow="never" style="height: 1700px" v-loading.fullscreen.lock=isLoading>
            <div style="padding-left: 50px ;display: flex">
              <h1 style="font-size: 30px ;font-weight: 550 ;line-height: 1.5 ;padding-left: 1px">
                定制你的{{ goodPhoneList[0].name }}
              </h1>
              <div style="font-size: 20px; color: #6e6e73 ;padding-left: 180px;padding-top: -50px">——租用方案</div>
            </div>
            <el-divider content-position="right">
              <div class="gradient-text">share科技</div>
            </el-divider>


            <div style="display: flex">
              <div style="font-size: 18px ;font-weight: 300 ;padding-top: 10px;padding-right: 30px">
                请输入你的地址
              </div>
              <div v-if="addressList.length > 0">
                <el-select v-model="form.address" placeholder="请选择地址">
                  <el-option
                    v-for="item in addressOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">

                  </el-option>
                </el-select>
              </div>
              <div v-else>
                <el-select v-model="form.address" disabled placeholder="请选择地址">
                  <el-option
                    v-for="item in addressOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </div>


            </div>


            <el-form label-position="top" ref="formRef" :rules="formRules" :model="form">
              <el-form-item prop="address">
                <el-input
                  style="padding-top: 20px ;padding-bottom: 0px;padding-right: 100px "
                  type="textarea"
                  :rows="2"
                  placeholder="北京 北京市 海淀区 中关村大街100号"
                  v-model="form.address">
                </el-input>
              </el-form-item>
            </el-form>

            <div style="font-size: 18px ;font-weight: 300 ;padding-top: -30px ;">
              选择你租用的时间
            </div>
            <div style="display: flex">
              <div class="block" style="padding-top: 20px; padding-right: 10px">
                <el-date-picker
                  v-model="value1"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  @change="calculateResult">
                </el-date-picker>
              </div>
              <div style="padding-top: 25px; padding-right:
              10px;font-size: 18px ;font-weight: 300 ">
                共 {{ daysDifference }} 天
              </div>
            </div>

            <div style="font-size: 18px ;font-weight: 300 ;padding-top: 30px ;">
              请输入你的手机号
            </div>
            <div style="display: flex; ">
              <el-input
                style="padding-top: 20px; padding-right: 10px"
                placeholder="手机号？"
                v-model="form.phonenumber"
                @input="calculateResult">
              </el-input>
              <span style="padding-top: 27px; padding-right: 500px"></span>
            </div>


            <div style
                   ="padding-top: 30px">
              <el-divider></el-divider>
            </div>
            <div>
              <div style="padding-left: 100px ;line-height: 2 ;color: #5a5e66">
                租赁服务免责声明<br>
                第一条 订单时效规则<br>
                用户需在使用日前3个自然日23:59前完成下单（例：9月30日使用须于9月27日前下单）<br>
                当天下单视为紧急租赁需求，商家有权拒绝发货且不承担违约责任<br>
                第二条 发货与取消条款<br>
                物流单号生成即视为发货完成，用户不可取消订单（依据《电子商务法》第49条）<br>
                第三条 提前归还规则<br>
                提前归还对已支付租金的订单不予退还（《民法典》第566条）<br>
                第四条 违约责任<br>
                逾期未归还设备满72小时：<br>
                按日租金300%计收滞纳金（参照《民法典》第584条）<br>
                逾期达30日历日：<br>
                向用户所在地法院提起民事诉讼（诉讼标的含设备价值+3倍租金损失）<br>
                向公安机关报案涉嫌侵占罪（《刑法》第270条）<br>
                同时联系管理员对账号进行删除<br>
                故意损坏行为将依法追究惩罚性赔偿责任<br>
                第六条 争议解决<br>
                本声明作为《用户协议》不可分割部分，具有同等法律效力<br>
                争议管辖法院为平台主体所在地中级人民法院<br>
                用户数据将作为电子证据提交司法机构（《电子签名法》第5条）<br>
                第七条 强制措施<br>
                平台有权通过合法渠道追偿欠款（包括但不限于诉讼保全、申请强制执行）<br>
                涉案设备将启用GPS定位追踪及远程锁定功能（符合《网络安全法》第27条）<br>
              </div>
            </div>

            <div style="padding-top: 30px">
              <el-divider></el-divider>
            </div>
            <div style="display: flex ;padding-left: 250px;padding-top: 20px ;color:#5a5e66 ">
              <div>小计</div>
              <div style="padding-left: 250px;font-weight:550 ;padding-top: 4px"> RMB {{ result }}</div>
            </div>
            <div style="display: flex ;padding-left: 250px;padding-top: 20px ;color:#5a5e66 ">
              <div>运费</div>
              <div style="font-weight:550 ;padding-left: 270px;padding-top: 4px"> 免费</div>
            </div>
            <div style="padding-top: -10px;padding-left: 250px">
              <el-divider></el-divider>
            </div>
            <div
              style="display: flex ; font-size:25px; font-weight :550;padding-left: 250px;padding-top: 1px ;color:#5a5e66 ">
              <div>总计</div>
              <div style="padding-left: 210px;padding-top: 4px"> RMB {{ result }}</div>
            </div>
            <div>
              <button class="custom-button" @click="openDialog">付款</button>
            </div>
          </el-card>


        </div>
      </el-row>
    </el-container>


    <el-dialog :visible.sync="dialogVisible" title="下单成功">
      <div style="display: flex;">

        <div class="image" style="height: 300px; width: 250px; margin-right: 20px;" @mousemove="handleMouseMove"
             @mouseleave="handleMouseLeave">
          <img :src="'http://localhost:8080' + goodPhoneList[0].img" alt="">
        </div>
        <div>
          <div style="display: flex; justify-content: space-between;">
            <div style="font-size: 20px ">
              租用{{ goodPhoneList[0].name }}
            </div>
            <div style=" font-size:20px;padding-left: 100px">
              {{ form.time }}天
            </div>
          </div>
          <el-divider></el-divider>
          <div style=" font-size:20px; justify-content: space-between;">
            送至：{{ form.address }}
          </div>
          <div style="display: flex ;padding-top: 20px">
            <div style="font-size: 20px ; justify-content: space-between; ">
              订单金额:
            </div>
            <div style=" font-size:20px;padding-left: 120px">
              RMB{{ result }}
            </div>
          </div>
          <div style="padding-top: 20px; justify-content: space-between;">
            创建时间: {{ form.buildTime }}
          </div>
          <el-divider></el-divider>
          <el-steps :active="2" style="padding-top: 20px">
            <el-step title="已下单"></el-step>
            <el-step title="等待配送"></el-step>
            <el-step title="订单完成"></el-step>
          </el-steps>
        </div>
      </div>
      <template #footer>
        <el-button @click="dialogVisible = false">确定</el-button>
      </template>

    </el-dialog>


    <el-dialog
      :visible.sync="qrDialogVisible"
      title="扫码支付"
      width="30%"
      class="apple-dialog"
      center>
      <div style="text-align:center">
        <!-- 二维码占位图 -->
        <div style="color:#666; margin:10px 0">请使用手机扫码完成支付</div>
        <el-button
          type="primary"
          class="apple-button primary-button"
          @click="handlePaid">
          我已完成付款
        </el-button>
        <div style="margin-top:15px; font-size:12px; color:#999">
          支付倒计时: {{ countdown }}秒
        </div>
      </div>
    </el-dialog>


  </div>
</template>


<script>
import {listGoods} from "@/api/system/goods";
import {addInfo} from "@/api/system/info";
import {getUserProfile} from "@/api/system/user";
import {listEvalute} from "@/api/system/evalute";
import {listAddress} from "@/api/system/address";


export default {
  name: "FrontShopping",

  data() {
    return {

      qrDialogVisible: false,  // 新增二维码对话框状态
      countdown: 300,         // 5分钟倒计时
      timer: null,           // 定时器

      formRules: {
        address: [
          {required: true, message: '请输入地址', trigger: ['blur', 'change']}
        ]
      },
      daysDifference: 0,
      productId: '',
      goodPhoneList: [],
      queryParams: {},
      result: 0,
      input: '',
      dialogVisible: false,
      isLoading: false, // 新增加载状态
      form: {},
      user: {},

      addressOptions: [
        {
          value: '',
          label: '默认地址 '
        },
        {
          value: '',
          label: '备选地址'
        }
      ],
      addressList: [],


      name: '',
      evaluteList: [],
      statusForm: {
        status: 0,
      },
      value1: '',
    }
  },


  created: function () {
    this.productId = this.$route.query.id;
    this.getGoodList();
    this.getUser();
    this.getEvaluteList();

    this.name = this.goodPhoneList[0].busName;
  },


  methods: {
    getStatusText(status) {
      switch (status) {
        case 0:
          return '好评';
        case 1:
          return '中评';
        case 2:
          return '差评';
        default:
          return '未知状态';
      }
    },
    // 根据状态返回对应的类名
    getStatusClass(status) {
      switch (status) {
        case 0:
          return 'status-good';
        case 1:
          return 'status-medium';
        case 2:
          return 'status-bad';
        default:
          return '';
      }
    },

    getAddress(id) {
      this.queryParams.userId = id;
      listAddress(this.queryParams).then(response => {
        this.addressList = response.rows;
        console.log(this.addressList);
        console.log(this.addressOptions);
        if (this.addressList.length > 1) {
          console.log("if");
          this.addressOptions[0].value = this.addressList[0].userAddress;
          this.addressOptions[1].value = this.addressList[1].userAddress;
          this.addressOptions[1].label = '备选地址';
        } else if (this.addressList.length === 1) {
          console.log("if else");
          this.addressOptions[0].value = this.addressList[0].userAddress;
          this.addressOptions[1].value = '';
          this.addressOptions[1].label = '暂无备选地址';
        } else {
          this.addressOptions[0].value = '';
          this.addressOptions[0].label = '默认地址 ';
          this.addressOptions[1].value = '';
          this.addressOptions[1].label = '暂无备选地址';
        }


        console.log(this.addressOptions);


      });
    },

    getEvaluteList() {
      this.queryParams = {};
      this.queryParams.goodId = this.productId;
      listEvalute(this.queryParams).then(response => {
        this.evaluteList = response.rows;
      });
    },

    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.form.phonenumber = this.user.phonenumber;
        this.getAddress(this.user.userId);
      });
    },


    openDialog() {
      let errorMessage = '';
      if (!this.form.address) {
        errorMessage += '请输入地址；';
      }
      if (!this.value1) {
        errorMessage += '请选择租用时间；';
      }
      if (!this.form.phonenumber) {
        errorMessage += '请输入手机号；';
      } else {
        // 验证手机号码格式
        const phoneRegex = /^1[3-9]\d{9}$/;
        if (!phoneRegex.test(this.form.phonenumber)) {
          errorMessage += '请输入正确的手机号码格式；';
        }
      }
      if (errorMessage) {
        this.$modal.msgError(errorMessage);
        return;
      }

      // 显示二维码对话框并开始倒计时
      this.qrDialogVisible = true;
      this.startCountdown();
    },

    startCountdown() {
      this.countdown = 300;
      this.timer = setInterval(() => {
        if (this.countdown > 0) {
          this.countdown--;
        } else {
          this.closePayment();
          this.$modal.msgWarning('支付超时，请重新下单');
        }
      }, 1000);
    },

    calculateResult() {
      if (this.value1 && this.value1.length === 2) {
        const startDate = new Date(this.value1[0]);
        const endDate = new Date(this.value1[1]);
        const oneDay = 24 * 60 * 60 * 1000; // 一天的毫秒数
        this.daysDifference = Math.round(Math.abs((endDate - startDate) / oneDay)); // 计算天数差值
        this.form.time = this.daysDifference;
        if (this.goodPhoneList.length > 0) {
          this.result = this.daysDifference * this.goodPhoneList[0].price; // 计算总费用
        }
      } else {
        this.daysDifference = 0;
        this.result = 0;
      }
    },
    generateOrderId() {
      // 获取当前时间戳
      const timestamp = Date.now();
      // 生成一个 0 - 9999 之间的随机数，并补零至 4 位
      const randomNum = Math.floor(Math.random() * 10000).toString().padStart(4, '0');
      // 拼接时间戳和随机数
      const orderIdStr = `${timestamp}${randomNum}`;
      // 将拼接后的字符串转换为 BigInt 类型
      return orderIdStr;
    },


    // 新增付款确认处理
    async handlePaid() {
      try {
        // 关闭支付对话框
        this.closePayment();

        // 显示加载状态
        this.isLoading = true;

        // 模拟支付成功等待
        await new Promise(resolve => setTimeout(resolve, 1000));

        // 生成订单
        await this.addOrder();

        // 显示订单详情
        this.dialogVisible = true;
      } catch (error) {
        this.$modal.msgError('支付失败，请重试');
      } finally {
        this.isLoading = false;
      }
    },

    // 新增关闭支付方法
    closePayment() {
      clearInterval(this.timer);
      this.qrDialogVisible = false;
      this.countdown = 300;
    },


    async addOrder() {
      try {
        const buildTime = new Date().toISOString();
        const orderId = this.generateOrderId();
        const busName = this.goodPhoneList[0].busName;
        const userId = this.user.userId;
        const goodId = this.goodPhoneList[0].id;
        const status = 0;
        const price = this.result

        this.form = {
          ...this.form,
          buildTime,
          orderId,
          busName,
          userId,
          goodId,
          status,
          price,
        };

        await addInfo(this.form);
        this.$modal.msgSuccess("付款成功");
      } catch (error) {
        this.$modal.msgError("订单生成失败：" + error.message);
        throw error; // 抛出错误供上层处理
      }

    },


    getGoodList() {
      this.queryParams.id = this.productId;
      listGoods(this.queryParams).then(response => {
        this.goodPhoneList = response.rows;
      });
    },

    handleMouseMove(event) {
      const img = event.currentTarget;
      const rect = img.getBoundingClientRect();
      const x = event.clientX - rect.left;
      const y = event.clientY - rect.top;
      let rx = ((y / rect.height) - 0.5) * 20;
      let ry = ((x / rect.width) - 0.5) * -20;

      rx = Math.max(-10, Math.min(10, rx));
      ry = Math.max(-10, Math.min(10, ry));
      img.style.setProperty('--rx', `${rx}deg`);
      img.style.setProperty('--ry', `${ry}deg`);
    },
    handleMouseLeave(event) {
      const img = event.currentTarget;

      img.style.setProperty('--rx', '0deg');
      img.style.setProperty('--ry', '0deg');

      img.style.transform = `perspective(500px) rotateX(0deg) rotateY(0deg) scale(1)`;
    }
  },


}


</script>


<style scoped lang="scss">


.image {
  perspective: 500px; /* 设置透视效果 */
}

.image img {
  display: block;
  max-width: 100%;
  height: auto;
  transform: perspective(500px) rotateX(var(--rx, 0deg)) rotateY(var(--ry, 0deg)) scale(1);
  /* 调整过渡时间和缓动函数 */
  transition: transform 0.3s cubic-bezier(0.25, 0.46, 0.45, 0.94);
}


.gradient-text {
  background: linear-gradient(to right, #ff0000, #008000); /* 设置渐变的方向从左到右，颜色从 #ff0000 到 #ffff00 */
  /* 添加标准的 background-clip 属性 */
  background-clip: text;
  -webkit-background-clip: text; /* 将设置的背景颜色限制在文字中，兼容 WebKit 内核浏览器 */
  -webkit-text-fill-color: transparent; /* 给文字设置成透明，兼容 WebKit 内核浏览器 */
}

.blackWhite-text {
  background: linear-gradient(to right, #FFF1D6, #DFC195); /* 设置渐变的方向从左到右，颜色从 #ff0000 到 #ffff00 */
  /* 添加标准的 background-clip 属性 */
  background-clip: text;
  -webkit-background-clip: text; /* 将设置的背景颜色限制在文字中，兼容 WebKit 内核浏览器 */
  -webkit-text-fill-color: transparent; /* 给文字设置成透明，兼容 WebKit 内核浏览器 */
}

.custom-button {
  background-color: #0071e3; /* 设置按钮的背景颜色 */
  color: white; /* 设置按钮文字的颜色 */
  border: none; /* 去除按钮的边框 */
  /* 增大内边距，让按钮变大 */
  padding: 15px 60px;
  border-radius: 10px; /* 设置按钮的圆角 */
  cursor: pointer; /* 设置鼠标悬停时的指针样式 */
  /* 让按钮靠左显示 */
  float: right;
  margin-top: 30px;

  margin-right: 170px;
}

.order-container {
  /* 核心苹果设计特征 */
  border: 0.5px solid rgba(0, 0, 0, 0.08); /* 超细半透明边框 */
  background: linear-gradient(
      152deg,
      rgba(255, 255, 255, 0.96) 0%,
      rgba(250, 250, 252, 0.96) 100%
  ); /* 玻璃质感渐变 */
  border-radius: 14px; /* 精确的圆角半径 */
  backdrop-filter: blur(12px); /* 毛玻璃效果 */
  box-shadow: inset 0 1px 0.5px rgba(255, 255, 255, 0.6), /* 内发光 */
  0 4px 24px rgba(0, 0, 0, 0.04), /* 基础投影 */
  0 1px 2px rgba(0, 0, 0, 0.03); /* 精细边缘阴影 */
  transition: all 0.36s cubic-bezier(0.28, 0.44, 0.4, 1);

  /* 新版空间布局 */
  padding: 20px;
  margin-bottom: 16px;
  position: relative;
}

/* 动态深度效果 */
.order-container:hover {
  border-color: rgba(0, 0, 0, 0.12);
  box-shadow: inset 0 1px 0.8px rgba(255, 255, 255, 0.8),
  0 8px 32px rgba(0, 0, 0, 0.08),
  0 2px 4px rgba(0, 0, 0, 0.04);
  transform: translateY(-0.8px);
}


/* 悬停时增强状态指示 */
.order-container:hover::after {
  opacity: 0.24;
}

/* 好评状态的样式 */
.status-good {
  color: green;
}

/* 中评状态的样式 */
.status-medium {
  color: orange;
}

/* 差评状态的样式 */
.status-bad {
  color: red;
}
</style>
