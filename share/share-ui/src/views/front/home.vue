<template>
  <div>
    <div class="container-top">
      <div class="top-left">
        <h1>租出新境界，</h1>
        <br>
        <div class="top-left-bottom">共享新未来。</div>
      </div>

      <div class="co">
        <span style="padding-left: 1400px ">需要帮助？</span>
        <br>
        <el-button type="text" class="button" style="padding-left: 1400px"  @click="goToAi() ">查询帮助文档</el-button>
      </div>

    </div>


    <div class=" flex-container">

      <div class="temp" @click="goComputerPage ">
        <img
          :src="'http://localhost:8080' + '/profile/upload/2025/02/17/store-card-13-mac-nav-202410_20250217004847A001.png'"
          style="color: #f5f5f7" :width="120" :height="78" alt=""/>

        <div class="text-line" :style="{fontWeight: 550, paddingLeft: '40px', paddingTop: '15px', color: '#1d1d1f', }"
             @mouseenter="addUnderline" @mouseleave="removeUnderline">
          电脑
        </div>
      </div>
      <div class="temp" @click="goToPhonePage ">
        <img
          :src="'http://localhost:8080' + '/profile/upload/2025/02/17/store-card-13-iphone-nav-202409_20250217004905A002.png'"
          style="color: #f5f5f7" :width="120" :height="78" alt=""/>

        <div class="text-line" :style="{fontWeight: 550, paddingLeft: '40px', paddingTop: '15px', color: '#1d1d1f', }"
             @mouseenter="addUnderline" @mouseleave="removeUnderline">
          手机
        </div>
      </div>
      <div class="temp" @click="goWatchPage ">
        <img
          :src="'http://localhost:8080' + '/profile/upload/2025/02/17/store-card-13-watch-nav-202409_20250217004914A003.png'"
          style="color: #f5f5f7" :width="120" :height="78" alt=""/>

        <div class="text-line" :style="{fontWeight: 550, paddingLeft: '40px', paddingTop: '15px', color: '#1d1d1f', }"
             @mouseenter="addUnderline" @mouseleave="removeUnderline">
          手表
        </div>
      </div>
      <div class="temp" @click="goIpadPage ">
        <img
          :src="'http://localhost:8080' + '/profile/upload/2025/02/17/store-card-13-ipad-nav-202405_20250217004927A004.png'"
          style="color: #f5f5f7" :width="120" :height="78" alt=""/>

        <div class="text-line" :style="{fontWeight: 550, paddingLeft: '40px', paddingTop: '15px', color: '#1d1d1f', }"
             @mouseenter="addUnderline" @mouseleave="removeUnderline">
          平板
        </div>
      </div>
      <div class="temp" @click="goPlanePage ">
        <img
          :src="'http://localhost:8080' + '/profile/upload/2025/02/17/9af712f2995f93c87be7fd858ff4fea3_20250217004937A005.png'"
          style="color: #f5f5f7" :width="120" :height="78" alt=""/>

        <div class="text-line" :style="{fontWeight: 550, paddingLeft: '40px', paddingTop: '15px', color: '#1d1d1f', }"
             @mouseenter="addUnderline" @mouseleave="removeUnderline">
          无人机
        </div>
      </div>
      <div class="temp" @click="goCameraPage ">
        <img
          :src="'http://localhost:8080' + '/profile/upload/2025/02/17/d41e54fce7cf0951294aa1226ad3f070_20250217004945A006.png'"
          style="color: #f5f5f7" :width="120" :height="78" alt=""/>

        <div class="text-line" :style="{fontWeight: 550, paddingLeft: '40px', paddingTop: '15px', color: '#1d1d1f', }"
             @mouseenter="addUnderline" @mouseleave="removeUnderline">
          相机
        </div>
      </div>


    </div>


    <div style="padding-top: 70px">
      <span class="black">来看看，</span>
      <span class="grey">手机吧，这些都是最新的。</span>
      <div class="scrollable-container" style="padding-left: 70px" ref="phoneScrollContainer"
           @mouseenter="showPhoneButtons" @mouseleave="hidePhoneButtons"
          >
        <!-- 这里放滚动内容 -->
        <div v-for="(item, index) in goodPhoneList" :key="index" class="item-container">
          <!-- 商品卡片内容 -->
          <el-row>
            <div style="width: 300px; height: 500px;">
              <el-card class="el-card" :body-style="{ padding: '20px' } " shadow="hover">
                <div class="image-container">
                  <img style="color: #f5f5f7" :src="'http://localhost:8080' + item.img" height="250px" width="200px"
                       alt=""/>
                </div>
                <div style="padding: 14px;">
                  <span style="font-size: 18px ;font-weight: 600">{{ item.name }}</span>
                  <div class="bottom clearfix">
                    <time class="time">RMB {{ item.price }}/天</time>
                    <el-button type="text" class="button" style="padding-left: 60px" @click="goShoppingPage(item.id)">
                      立即下单{{ item.id}}
                    </el-button>
                  </div>
                </div>
              </el-card>
            </div>
          </el-row>
        </div>
        <el-button @click="scrollPhoneLeft" icon="el-icon-arrow-left" class="custom-scroll-button left-button"
                   :style="{ display: showPhoneScrollButtons ? 'flex' : 'none' }"></el-button>
        <el-button @click="scrollPhoneRight" icon="el-icon-arrow-right" class="custom-scroll-button right-button"
                   :style="{ display: showPhoneScrollButtons ? 'flex' : 'none' }"></el-button>
      </div>
    </div>


    <div style="padding-top: 70px">
      <span class="black">来看看电脑，</span>
      <span class="grey">高效的生产力工具。</span>
      <div class="scrollable-container" ref="comScrollContainer" style="padding-left: 70px" @mouseenter="showComButtons"
           @mouseleave="hideComButtons"
           >
        <!-- 这里放滚动内容 -->
        <div v-for="(item, index) in goodComList" :key="index" class="item-container">
          <!-- 商品卡片内容 -->
          <el-row>
            <div style="width: 300px; height: 500px;">
              <el-card class="el-card" :body-style="{ padding: '20px' } " shadow="hover">
                <div class="image-container">
                  <img style="color: #f5f5f7" :src="'http://localhost:8080' + item.img" height="250px" width="250px"
                       alt=""/>
                </div>
                <div style="padding: 14px;">
                  <span style="font-size: 18px ;font-weight: 600">{{ item.name }}</span>
                  <div class="bottom clearfix">
                    <time class="time">RMB {{ item.price }}/天</time>
                    <el-button type="text" class="button" style="padding-left: 70px" @click="goShoppingPage(item.id)">立即下单</el-button>
                  </div>
                </div>
              </el-card>
            </div>
          </el-row>
        </div>
        <el-button @click="scrollComLeft" icon="el-icon-arrow-left" class="custom-scroll-button com-left-button"
                   :style="{ display: showComScrollButtons ? 'flex' : 'none' }"></el-button>
        <el-button @click="scrollComRight" icon="el-icon-arrow-right" class="custom-scroll-button com-right-button"
                   :style="{ display: showComScrollButtons ? 'flex' : 'none' }"></el-button>
      </div>
    </div>


    <div style="padding-top: 70px">
      <span class="black">让闲置产生价值，</span>
      <span class="grey">选择share的理由。</span>
      <div class="card-container" style="padding-left: 70px ;padding-top: 30px">


        <el-row>
          <el-card style="width: 200px; height: 150px;" :body-style="{ padding: '20px',boxSizing:'border-box' } ">
            <div style="font-weight: 550">
              <i class="el-icon-wallet" style="font-size: 30px;color: #1ab394 "> </i>
              <br>
              最长可享
              <span style="color: #1ab394 ">6个月
              </span>
              免息分期。
            </div>
          </el-card>
        </el-row>

        <el-row>
          <el-card style="width: 200px; height: 150px;" :body-style="{ padding: '20px',boxSizing:'border-box' } ">
            <div style="font-weight: 550">
              <i class="el-icon-s-cooperation" style="font-size: 30px;color: #007aff "> </i>
              <br>
              所有订单高效配送，
              <span style="color: #007aff ">最快3小时可达。
              </span>
            </div>
          </el-card>
        </el-row>

        <el-row>
          <el-card style="width: 200px; height: 150px;" :body-style="{ padding: '20px',boxSizing:'border-box' } ">
            <div style="font-weight: 550">
              <i class="el-icon-refresh" style="font-size: 30px;color: #b650ff "> </i>
              <br>
              share
              <span style="color: #b650ff">致力于低碳环保，让每一份你的闲置都有价值。
              </span>
            </div>
          </el-card>
        </el-row>

        <el-row>
          <el-card style="width: 200px; height: 150px;" :body-style="{ padding: '20px',boxSizing:'border-box' } ">
            <div style="font-weight: 550">
              <i class="el-icon-apple" style="font-size: 30px;color: #ffa500 "> </i>
              <br>
              专属于你的购物体验
            </div>
          </el-card>
        </el-row>

        <el-row>
          <el-card style="width: 200px; height: 150px;" :body-style="{ padding: '20px',boxSizing:'border-box' } ">
            <div style="font-weight: 550">
              <i class="el-icon-present" style="font-size: 30px;color: #ff0000 "> </i>
              <br>
              每一次交易，share都会向
              <span style="color: #ff0000">环保公益组织捐赠。
              </span>
            </div>
          </el-card>
        </el-row>

        <el-row>
          <el-card style="width: 200px; height: 150px;" :body-style="{ padding: '20px',boxSizing:'border-box' } ">
            <div style="font-weight: 550">
              <i class="el-icon-chat-round" style="font-size: 30px;color: #a4da89 "> </i>
              <br>
              祝你今天有个好心情。
            </div>
          </el-card>
        </el-row>

      </div>
    </div>


    <div style="padding-top: 70px">
      <span class="black">来看看无人机吧，</span>
      <span class="grey">都是一些有意思的东西。</span>
      <div class="scrollable-container" ref="planeScrollContainer" style="padding-left: 70px"
           @mouseenter="showPlaneButtons"
           @mouseleave="hidePlaneButtons"
           >
        <!-- 这里放滚动内容 -->
        <div v-for="(item, index) in goodPlaneList" :key="index" class="item-container">
          <!-- 商品卡片内容 -->
          <el-row>
            <div style="width: 300px; height: 500px;">
              <el-card class="el-card" :body-style="{ padding: '20px' }  " shadow="hover">
                <div class="image-container">
                  <img style="color: #f5f5f7" :src="'http://localhost:8080' + item.img" height="250px" width="250px"
                       alt=""/>
                </div>
                <div style="padding: 14px;">
                  <span style="font-size: 18px ;font-weight: 600">{{ item.name }}</span>
                  <div class="bottom clearfix">
                    <time class="time">RMB {{ item.price }}/天</time>
                    <el-button type="text" class="button" style="padding-left: 70px" @click="goShoppingPage(item.id)">立即下单</el-button>
                  </div>
                </div>
              </el-card>
            </div>
          </el-row>
        </div>
        <el-button @click="scrollPlaneLeft" icon="el-icon-arrow-left" class="custom-scroll-button plane-left-button"
                   :style="{ display: showPlaneScrollButtons ? 'flex' : 'none' }"></el-button>
        <el-button @click="scrollPlaneRight" icon="el-icon-arrow-right" class="custom-scroll-button plane-right-button"
                   :style="{ display: showPlaneScrollButtons ? 'flex' : 'none' }"></el-button>
      </div>
    </div>


    <div class="grey" style="padding-top: 30px;padding-left: 200px;padding-right: 200px ;padding-bottom: 50px">

      <h5 style="text-align: center">Share 绿色环保声明</h5>
      <h6 class="h6">在 Share，我们坚信科技的力量不仅在于改变生活，更在于守护地球。我们致力于通过共享经济模式，减少资源浪费，降低碳排放，为绿色环保贡献一份力量。
        在 Share，我们坚信科技的力量不仅在于改变生活，更在于守护地球。我们深知，电子设备的制造和使用对环境造成了巨大的负担，因此，我们致力于通过共享经济模式，减少资源浪费，降低碳排放，为绿色环保贡献一份力量。<br>
        我们的承诺：<br>
        减少电子废弃物<br>
        电子废弃物是全球增长最快的垃圾类型之一，对土壤、水源和空气造成了严重污染。在
        Share，我们通过设备租赁和共享，延长了每一台设备的使用寿命，减少了电子废弃物的产生。我们鼓励用户将闲置设备共享给他人使用，并通过专业的设备维护和翻新服务，确保每一台设备都能发挥最大价值。我们相信，减少浪费是保护地球的第一步。<br>
        推动循环经济<br>
        传统的“购买-使用-丢弃”模式对地球资源的消耗是巨大的。在
        Share，我们倡导“使用而非拥有”的理念，推动循环经济的发展。通过我们的平台，用户可以轻松租用所需的设备，而无需购买全新的产品。这不仅降低了用户的使用成本，也减少了对自然资源的过度开采。我们与制造商合作，推广可回收材料和模块化设计，让每一台设备都能在生命周期结束后被高效回收和再利用。<br>
        降低碳足迹<br>
        我们深知，物流和运营过程中的碳排放对环境的影响不容忽视。因此，我们不断优化供应链和运输流程，采用环保包装材料，并优先选择低碳运输方式。我们还通过智能算法，将设备租赁需求与最近的库存点匹配，减少运输距离和能源消耗。此外，我们在办公和运营中积极推行无纸化操作，并使用可再生能源，努力将碳足迹降到最低。<br>
        支持可持续发展<br>
        环保不仅仅是我们的责任，更是我们的使命。我们与全球领先的环保组织合作，积极参与植树造林、清洁能源推广和海洋保护等项目。每完成一笔租赁交易，我们都会将部分收益捐赠给环保事业，用于支持可持续发展项目。我们还定期举办环保主题活动，鼓励用户参与，共同为地球的未来贡献力量。<br>
      </h6>
      <u> </u>

      <h6 class="h6">
        我们的愿景：<br>
        在 Share，我们相信，每一个小小的选择都能为地球带来巨大的改变。通过共享，我们不仅让科技更触手可及，也让未来更绿色、更可持续。我们希望通过我们的努力，让更多人意识到环保的重要性，并加入到绿色生活的行列中。<br>
        加入我们：<br>
        选择 Share，选择一种更环保的生活方式。无论是租用一台设备，还是将闲置设备共享给他人，你都在为地球的未来贡献力量。让我们一起，为地球的未来，共享绿色，共享美好。<br>

      </h6>
      <h5 style="text-align: right">Share – 共享科技，守护地球。</h5>

      <div class="single-underline"></div>
      <div>
        <h6>
          Copyright © 2023 Share 共享科技，守护地球。
          沪ICP备2023000000号-1
        </h6>
      </div>
    </div>


  </div>
</template>


<script>
import {listGoodTpye} from "@/api/system/goodTpye";
import {listGoods} from "@/api/system/goods";


export default {
  name: 'Home',
  components: {Image},

  data() {
    return {
      goodTpyeList: [],
      goodPhoneList: [],
      goodComList: [],
      goodPlaneList: [],

      showPhoneScrollButtons: false,
      showComScrollButtons: false,
      showPlaneScrollButtons: false,

      underlinedIndexes: [], // 用于记录哪些元素需要添加下划线



      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },

    }
  },
  created() {
    this.getTypeList();
    this.getGoodPhoneList();
    this.getGoodComList();
    this.getPlaneList();

  },
  methods: {

    getTypeList() {
      listGoodTpye(this.queryParams).then(response => {
        this.goodTpyeList = response.rows;
      });
    },
    getGoodPhoneList() {
      this.queryParams.status = 1;
      this.queryParams.type = 3;
      listGoods(this.queryParams).then(response => {
        this.goodPhoneList = response.rows;
      });
    },
    getGoodComList() {
      this.queryParams.status = 1;
      this.queryParams.type = 2;
      listGoods(this.queryParams).then(response => {
        this.goodComList = response.rows;
      });
    },
    getPlaneList() {
      this.queryParams.status = 1;
      this.queryParams.type = 6;
      listGoods(this.queryParams).then(response => {
        this.goodPlaneList = response.rows;
      });
    },

    addUnderline(index) {
      if (!this.underlinedIndexes.includes(index)) {
        this.underlinedIndexes.push(index);
      }
    },
    removeUnderline(index) {
      const indexToRemove = this.underlinedIndexes.indexOf(index);
      if (indexToRemove > -1) {
        this.underlinedIndexes.splice(indexToRemove, 1);
      }
    },

    goToAi() {
      this.$router.push('/front/ai');
    },
    goToPhonePage() {
      this.$router.push('/front/phone');
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
    goWatchPage() {
      this.$router.push('/front/watch');
    },
    goIpadPage() {
      this.$router.push('/front/ipad');
    },

    goShoppingPage(id) {
      this.$router.push({
        path: '/front/shopping',
        query: {
          id: id
        }
      })
    },

    scrollPhoneLeft() {
      const container = this.$refs.phoneScrollContainer;
      container.scrollTo({
        left: container.scrollLeft - 300,
        behavior: 'smooth'
      });
    },
    scrollPhoneRight() {
      const container = this.$refs.phoneScrollContainer;
      container.scrollTo({
        left: container.scrollLeft + 300,
        behavior: 'smooth'
      });
    },
    scrollComLeft() {
      const container = this.$refs.comScrollContainer;
      container.scrollTo({
        left: container.scrollLeft - 300,
        behavior: 'smooth'
      });
    },
    scrollComRight() {
      const container = this.$refs.comScrollContainer;
      container.scrollTo({
        left: container.scrollLeft + 300,
        behavior: 'smooth'
      });
    },
    scrollPlaneLeft() {
      const container = this.$refs.planeScrollContainer;
      container.scrollTo({
        left: container.scrollLeft - 300,
        behavior: 'smooth'
      });
    },
    scrollPlaneRight() {
      const container = this.$refs.planeScrollContainer;
      container.scrollTo({
        left: container.scrollLeft + 300,
        behavior: 'smooth'
      });
    },
    showPhoneButtons() {
      this.showPhoneScrollButtons = true;
    },
    hidePhoneButtons() {
      this.showPhoneScrollButtons = false;
    },
    showComButtons() {
      this.showComScrollButtons = true;
    },
    hideComButtons() {
      this.showComScrollButtons = false;
    },
    showPlaneButtons() {
      this.showPlaneScrollButtons = true;
    },
    hidePlaneButtons() {
      this.showPlaneScrollButtons = false;
    }
  }


}

</script>


<style scoped lang="scss">


.temp {
  cursor: pointer; /* 鼠标悬停时变为手指形状 */
}

.card-container {
  display: flex;
  flex-direction: row;
  gap: 20px; /* 设置元素之间的间隔 */
}

.top-left {
  padding-left: 70px;
  padding-top: 50px;

}

.top-left-bottom {
  font-size: 48px;
  line-height: 0.11;
  font-weight: 600;
  color: #6e6e73;
}

.co {
  font-size: 20px;

  font-weight: 600;
}

.black {
  font-size: 20px;
  padding-left: 70px;
  font-weight: 600;
}

.grey {
  font-size: 20px;
  color: #6e6e73;
  font-weight: 600;
}


.flex-container {
  display: flex; /* 使用 flexbox 布局 */
  flex-wrap: wrap; /* 允许换行 */
  gap: 30px; /* 设置元素之间的间距 */
  padding-left: 70px;
  padding-top: 70px;
  color: #f5f5f7;

}

.text-line:hover {

  text-decoration: underline; /* 鼠标划入时添加下划线 */
}

.el-image {
  /* display: flex; *//* 使用 flexbox 布局 */
  flex-wrap: wrap; /* 允许换行 */
  gap: 10px; /* 设置元素之间的间距 */
  padding-left: 20px;
  padding-top: 130px;
  white-space: nowrap;
  display: inline-block;

}

.el-card {
  border-radius: 30px;
  height: 400px;

}

.image-container {
  position: absolute;
  top: calc(100% / 5);
  left: 50%;
  transform: translateX(-50%);
  width: 250px;
  /* 这里可以根据需要调整图片容器宽度 */
  text-align: center;


}

.im {
  width: 50px;
  height: 50px;
  vertical-align: top;
}


.reason-container {
  display: flex;
  overflow-x: auto;
  height: 450px;
  padding-top: 30px;
  -ms-overflow-style: none;
  scrollbar-width: none;
}

.scrollable-container {
  display: flex;
  overflow-x: auto;
  overflow-y: hidden;
  height: 450px;
  padding-top: 30px;
  -ms-overflow-style: none;
  scrollbar-width: none;
}

.scrollable-container::-webkit-scrollbar {
  display: none; /* Chrome, Safari 和 Opera */
}

.item-container {
  flex: 0 0 auto; /* 让子元素宽度由内容决定 */
  width: 300px; /* 明确设置子元素宽度 */
  margin-right: 10px; /* 子元素之间的间距 */
}

/* 自定义滑动按钮样式 */
.custom-scroll-button {
  position: absolute;
  top: calc(70% + 200px); /* 向下偏移 50px，可根据需求调整 */
  transform: translateY(-50%);
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.3);
  color: white;
  border: none;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: background-color 0.3s ease;
  cursor: pointer;
  z-index: 1; /* 确保按钮显示在最上层 */

  &:hover {
    background-color: rgba(0, 0, 0, 0.5);
  }
}

/* 左按钮位置 */
.left-button {
  left: 10px; /* 距离窗口左侧 10px */
  transform: translateY(-50%);
}

/* 右按钮位置 */
.right-button {

  right: 10px; /* 距离窗口右侧 10px */
  transform: translateY(-50%);
}

/* 电脑左按钮位置 */
.com-left-button {
  top: calc(130% + 230px); /* 向下偏移 50px，可根据需求调整 */
  left: 10px;
  transform: translateY(-50%);
}

/* 电脑右按钮位置 */
.com-right-button {
  top: calc(130% + 230px); /* 向下偏移 50px，可根据需求调整 */
  right: 10px;
  transform: translateY(-50%);
}


/* plane左按钮位置 */
.plane-left-button {
  top: calc(215% + 250px); /* 向下偏移 50px，可根据需求调整 */
  left: 10px;
  transform: translateY(-50%);
}

/* plane右按钮位置 */
.plane-right-button {
  top: calc(215% + 250px); /* 向下偏移 50px，可根据需求调整 */
  right: 10px;
  transform: translateY(-50%);
}

.h6 {
  line-height: 1.5; /* 设置行高为字体大小的 1.5 倍 */
}

.single-underline {
  width: 100%; /* 可以根据需要调整宽度 */
  border-bottom: 1px solid black; /* 下划线的样式，可修改颜色和粗细 */
  margin: 10px 0; /* 可以根据需要调整上下间距 */
  color: #6e6e73;
}
</style>
