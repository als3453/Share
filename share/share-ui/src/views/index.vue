<template>
  <div class="share-container">
    <!-- 主标题 -->
    <h1 class="share-title">Share<span class="title-gradient">Tech</span></h1>

    <!-- 副标题 -->
    <div class="subtitle-container">
      <div class="subtitle-line"></div>
      <h2 class="share-subtitle">硅基生命循环者</h2>
      <div class="subtitle-line"></div>
    </div>

    <!-- 简介段落 -->
    <p class="intro-text">
      我们诞生于电子垃圾堆叠的黄昏，成长于算力与生态的博弈场。作为全球首个「硬件轮回协议」制定者，Share科技以量子级精度重构科技消费的时空维度——
    </p>

    <!-- 核心模块 -->
    <div class="content-grid">
      <!-- 循环纪元架构师 -->
      <section-component
        title="循环纪元架构师"
        :items="architectureItems"
        accent-color="#0071E3"
      />

      <!-- 科技熵减革命 -->
      <section-component
        title="科技熵减革命"
        :items="revolutionItems"
        accent-color="#34C759"
      />

      <!-- 未来化石铸造者 -->
      <section-component
        title="未来化石铸造者"
        :items="legacyItems"
        accent-color="#FF9500"
      />
    </div>

    <!-- 动态数据展示 -->
    <div class="data-grid">
      <div
        v-for="(stat, index) in stats"
        :key="index"
        class="data-card"
        :style="{ borderLeftColor: stat.color }"
      >
        <h3 class="data-value">{{ stat.value }}</h3>
        <p class="data-label">{{ stat.label }}</p>
      </div>
    </div>

    <!-- 结尾段落 -->
    <div class="footer-section">
      <p class="footer-text">
        此刻，当您滑动屏幕的手指与某台平板的第15任宿主产生量子纠缠，Share的云端矩阵正将这次交互的0.00017焦耳能量，转化为撒哈拉太阳能电站的一粒光子。
      </p>
      <div class="signature">
        <p class="slogan">让每个像素都拥有七世轮回</p>
        <div class="logo">Share Tech</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    SectionComponent: {
      props: ['title', 'items', 'accentColor'],
      template: `
        <div class="section-card">
          <div class="section-header" :style="{ color: accentColor }">
            <div class="accent-bar" :style="{ backgroundColor: accentColor }"></div>
            <h3>{{ title }}</h3>
          </div>
          <ul class="section-list">
            <li
              v-for="(item, index) in items"
              :key="index"
              class="list-item"
            >
              <div class="bullet" :style="{ backgroundColor: accentColor }"></div>
              {{ item }}
            </li>
          </ul>
        </div>
      `
    }
  },
  data() {
    return {
      architectureItems: [
        '碳神经中枢：实时追踪设备从稀土开采到分子降解的全周期碳足迹',
        '轮回DNA库：记录设备在167个租赁周期的使用记忆与性能熵值',
        '云端拆解算法：预演电子元件在8种退役场景下的原子级重生路径'
      ],
      revolutionItems: [
        '387,952台设备跨越「购买-废弃」的单向诅咒',
        '每台硬件平均经历7.3次生命迭代',
        '电子垃圾产生量降低至行业标准的1/17'
      ],
      legacyItems: [
        '敦煌戈壁地下数据中心熔铸「文明黑曜石」',
        '镶嵌电路图腾的硅基晶体',
        '数字人类考古学的罗塞塔石碑'
      ],
      stats: [
        { value: '0.00017J', label: '量子纠缠能量转化', color: '#0071E3' },
        { value: '7世', label: '像素轮回代数', color: '#34C759' },
        { value: '2046', label: '月球记忆柱计划', color: '#FF9500' }
      ]
    }
  }
}
</script>

<style scoped>
/* 苹果风格基础 */
.share-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 40px 24px;
  background: #f5f5f7;
  font-family: -apple-system, BlinkMacSystemFont, sans-serif;
}

/* 标题系统 */
.share-title {
  font-size: 56px;
  font-weight: 700;
  letter-spacing: -1.2px;
  text-align: center;
  margin: 40px 0;
}

.title-gradient {
  background: linear-gradient(90deg, #0071E3 0%, #34C759 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.subtitle-container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 32px 0;
}

.subtitle-line {
  width: 80px;
  height: 1px;
  background: rgba(0,0,0,0.1);
  margin: 0 16px;
}

.share-subtitle {
  font-size: 24px;
  color: #86868b;
  font-weight: 500;
  letter-spacing: 0.3px;
}

/* 文本段落 */
.intro-text {
  font-size: 18px;
  line-height: 1.6;
  color: #1d1d1f;
  margin: 32px 0;
  padding: 24px;
  background: rgba(255,255,255,0.9);
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.03);
}

/* 内容网格系统 */
.content-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 24px;
  margin: 48px 0;
}

.section-card {
  background: #ffffff;
  border-radius: 18px;
  padding: 24px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
  transition: transform 0.3s ease;
}

.section-card:hover {
  transform: translateY(-2px);
}

.section-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  padding-bottom: 12px;
  border-bottom: 1px solid rgba(0,0,0,0.05);
}

.accent-bar {
  width: 4px;
  height: 24px;
  border-radius: 2px;
  margin-right: 12px;
}

.section-list {
  list-style: none;
  padding: 0;
}

.list-item {
  display: flex;
  align-items: center;
  padding: 12px 0;
  font-size: 17px;
  color: #1d1d1f;
  line-height: 1.4;
  border-bottom: 1px solid rgba(0,0,0,0.03);
}

.list-item:last-child {
  border-bottom: none;
}

.bullet {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  margin-right: 12px;
  flex-shrink: 0;
}

/* 数据卡片 */
.data-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;
  margin: 40px 0;
}

.data-card {
  background: #ffffff;
  padding: 24px;
  border-radius: 14px;
  border-left: 4px solid;
  box-shadow: 0 2px 8px rgba(0,0,0,0.03);
}

.data-value {
  font-size: 32px;
  font-weight: 700;
  letter-spacing: -0.5px;
  margin: 8px 0;
  color: #1d1d1f;
}

.data-label {
  font-size: 15px;
  color: #86868b;
  line-height: 1.4;
}

/* 底部样式 */
.footer-section {
  margin-top: 48px;
  padding: 32px;
  background: rgba(255,255,255,0.9);
  border-radius: 12px;
}

.footer-text {
  font-size: 16px;
  line-height: 1.6;
  color: #1d1d1f;
  margin-bottom: 24px;
}

.signature {
  border-top: 1px solid rgba(0,0,0,0.05);
  padding-top: 24px;
}

.slogan {
  font-size: 18px;
  color: #34C759;
  font-weight: 500;
  margin-bottom: 8px;
}

.logo {
  font-size: 24px;
  font-weight: 600;
  color: #0071E3;
  letter-spacing: 0.5px;
}
</style>
