computer.vue
<template>
  <div style="padding-left: 70px;padding-top: 50px; text-align: center">
    <div>
      <span>腕间芯片是压缩生命的量子沙漏，当你解绑时，它早已在充电盒的数据池析出时光熵，如沙粒倒流回云端漏斗重启计量圆周率。</span>
      <el-divider></el-divider>
    </div>


    <div v-if="!groupedGoodPhoneList.length">
      <div style="padding-top: 100px">
        <el-empty description="暂无商品"></el-empty>
      </div>
    </div>
    <div v-else>
      <el-row :gutter="-100" style="padding-top: 70px ;padding-right: 100px;padding-left: 100px">
        <!-- 设置列之间的水平间距为 20px -->
        <el-col :span="6" v-for="(item, index) in groupedGoodPhoneList.flat()" :key="index">
          <div class="card-wrapper"> <!-- 新增一个包裹元素用于设置上下间距 -->
            <div style="width: 300px; height: 500px;">
              <el-card class="el-card" shadow="hover">
                <div class="image-container">
                  <img style="color: #f5f5f7" :src="'http://localhost:8080' + item.img" height="250px" width="250px"
                       alt="">
                </div>
                <div style="padding: 14px;">
                  <span style="font-size: 18px ;font-weight: 600">{{ item.name }}</span>
                  <div class="bottom clearfix">
                    <time class="time">RMB {{ item.price }}/天</time>
                    <el-button type="text" class="button" style="padding-left: 70px"  @click="goShoppingPage(item.id)">立即下单</el-button>
                  </div>
                </div>
              </el-card>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>


  </div>
</template>

<script>
import {listGoods} from "@/api/system/goods";


export default {
  name: 'watch',

  data() {
    return {
      goodPhoneList: [],
      queryParams: {}
    }
  },
  created() {
    this.getGoodPhoneList();
  },

  computed: {
    groupedGoodPhoneList() {
      const result = [];
      for (let i = 0; i < this.goodPhoneList.length; i += 4) {
        result.push(this.goodPhoneList.slice(i, i + 4));
      }
      return result;
    }
  },
  methods: {
    goShoppingPage(id) {
      this.$router.push({
        path: '/front/shopping',
        query: {
          id: id
        }
      })
    },
    getGoodPhoneList() {
      this.queryParams.status = 1;
      this.queryParams.type = 4;
      listGoods(this.queryParams).then(response => {
        this.goodPhoneList = response.rows;
      });
    },


  }
}


</script>


<style scoped lang="scss">
.el-row {
  margin-left: -10px !important; /* 抵消 gutter 带来的左侧外边距 */
  margin-right: -10px !important; /* 抵消 gutter 带来的右侧外边距 */
}

.card-wrapper {
  margin: -40px 0 !important; /* 确保上下间距生效 */
}

.el-card {
  border-radius: 30px;
  height: 400px;
  margin-left: 1px !important; /* 抵消 gutter 带来的左侧外边距 */
  margin-right: 1px !important; /* 抵消 gutter 带来的右侧外边距 */
}

</style>
