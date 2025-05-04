<template>
  <div class="order-page">
    <!-- 订单列表 -->
    <div
      v-for="order in infoList"
      :key="order.id"
      class="order-container"
    >
      <div class="order-header">
        <div class="order-meta">
          <h3 class="order-number">订单号: {{ order.orderId }}</h3>
          <span class="order-date">创建时间: {{ formatTime(order.buildTime) }}</span>
        </div>
        <span class="order-status" :class="getStatusClass(order.status)">
          {{ getStatusText(order.status) }}
          <span v-if="order.userExpressId" class="return-tag">（已寄回）</span>
        </span>
      </div>

      <div class="order-content">
        <!-- 订单详情 -->
        <div class="detail-grid">
          <div class="detail-item">
            <span class="label">商品ID:</span>
            <span class="value">{{ order.goodId }}</span>
          </div>
          <div class="detail-item">
            <span class="label">租用时长:</span>
            <span class="value">{{ order.time }}天</span>
          </div>
          <div class="detail-item">
            <span class="label">订单金额:</span>
            <span class="value price">¥{{ order.price }}</span>
          </div>
          <div class="detail-item">
            <span class="label">收货地址:</span>
            <span class="value">{{ order.address }}</span>
          </div>
          <div class="detail-item">
            <span class="label">商家物流单号:</span>
            <span class="value">{{ order.expressId || '暂无物流信息' }}</span>
          </div>
          <div v-if="order.userExpressId" class="detail-item">
            <span class="label">用户寄回单号:</span>
            <span class="value return-express">{{ order.userExpressId }}</span>
          </div>
        </div>

        <!-- 操作按钮组 -->
        <div class="action-buttons">
          <!-- 确认收货（待收货状态） -->
          <el-button
            v-if="order.status === '1'"
            type="success"
            @click="confirmReceipt(order)"
          >
            确认收货
          </el-button>

          <!-- 寄回商品（已完成/已评价且未寄回） -->
          <el-button
            v-if="[2, 3].includes(Number(order.status)) && !order.userExpressId"
            type="warning"
            @click="openReturnDialog(order)"
          >
            寄回商品
          </el-button>

          <!-- 评价（已完成状态） -->
          <el-button
            v-if="order.status === '2'"
            type="primary"
            @click="openEvaluateDialog(order)"
          >
            发表评价
          </el-button>


            <!-- 取消订单（待发货状态） -->
            <el-button
              v-if="order.status === '0'"
              type="danger"
              @click="cancelOrder(order)"
            >
              取消订单
            </el-button>


        </div>
      </div>
    </div>



    <!-- 评价对话框 -->
    <el-dialog
      :visible.sync="evaluateDialogVisible"
      title="订单评价"
      width="600px"
    >
      <el-form label-position="top" ref="evaluateForm">
        <el-form-item label="评价内容" required>
          <el-input
            v-model="form.content"
            type="textarea"
            :rows="4"
            placeholder="请描述您的使用体验..."
            maxlength="200"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="评价等级" required>
          <el-radio-group v-model="form.status">
            <el-radio label="0">
              <span class="rating-good">👍 好评</span>
            </el-radio>
            <el-radio label="1">
              <span class="rating-neutral">👌 中评</span>
            </el-radio>
            <el-radio label="2">
              <span class="rating-bad">👎 差评</span>
            </el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>

      <template #footer>
        <div class="dialog-actions">
          <el-button @click="evaluateDialogVisible = false">取消</el-button>
          <el-button
            type="primary"
            :loading="isSubmittingEvaluation"
            @click="submitEvaluation"
          >
            提交评价
          </el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 寄回对话框 -->
    <el-dialog
      :visible.sync="returnDialogVisible"
      title="商品寄回"
      width="500px"
    >
      <el-form label-position="top" ref="returnFormRef" :rules="returnFormRules" :model="returnForm">
        <el-form-item label="快递单号" prop="expressId" required>
          <el-input
            v-model="returnForm.expressId"
            placeholder="请输入您寄回商品的快递单号"
            clearable
          />
        </el-form-item>
      </el-form>

      <template #footer>
        <div class="dialog-actions">
          <el-button @click="returnDialogVisible = false">取消</el-button>
          <el-button
            type="primary"
            :loading="isSubmittingReturn"
            @click="submitReturn"
          >
            确认寄回
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { listInfo, updateInfo,delInfo } from "@/api/system/info";
import { addEvalute } from "@/api/system/evalute";

export default {
  name: 'OrderList',
  data() {
    return {
      infoList: [],
      evaluateDialogVisible: false,
      returnDialogVisible: false,
      isSubmittingEvaluation: false,
      isSubmittingReturn: false,
      currentOrder: null,
      returnForm: {
        expressId: ''
      },
      form: {
        content: '',
        status: '',
        goodId: '',
        userId: ''
      },
      returnFormRules: {
        expressId: [
          { required: true, message: '请输入快递单号', trigger: ['blur', 'change'] },
          { pattern: /^\d+$/, message: '快递单号只能包含数字', trigger: ['blur', 'change'] }
        ]
      }
    };
  },
  created() {
    this.loadOrders();
  },
  methods: {
    async cancelOrder(order) {
      try {
        await this.$confirm('确认要取消该订单吗？', '取消确认', {
          confirmButtonText: '确定取消',
          cancelButtonText: '再想想',
          type: 'warning'
        });

        // 调用删除接口
        await delInfo(order.id);

        this.$modal.msgSuccess("订单已取消");
        this.loadOrders();
      } catch (error) {
        if (error !== 'cancel') {
          console.error('取消订单失败:', error);
          this.$modal.msgError("取消失败: " + (error.message || '服务器错误'));
        }


  }
},
    async loadOrders() {
      this.loading = true;
      try {
        const userId = this.$route.query.userId;
        if (!userId) {
          this.$modal.msgError("缺少用户ID参数");
          return;
        }

        const response = await listInfo({
          userId: userId
        });

        // 根据实际API响应结构调整
        if (response.code === 200 && response.rows) {
          this.infoList = response.rows.map(order => ({
            ...order,
            status: String(order.status),
            userExpressId: order.userExpressId || ''
          }));
        } else {
          this.$modal.msgError("订单数据格式错误");
        }
      } catch (error) {
        console.error('订单加载失败:', error);
        this.$modal.msgError("订单加载失败");
      } finally {
        this.loading = false;
      }
    },

    // 确认收货
    async confirmReceipt(order) {
      try {
        await updateInfo({
          ...order,
          status: '2',
          overTime: Date.now()
        });
        this.$modal.msgSuccess("确认收货成功");
        this.loadOrders();
      } catch (error) {
        console.error('确认收货失败:', error);
        this.$modal.msgError("操作失败，请稍后重试");
      }
    },

    // 打开评价对话框
    openEvaluateDialog(order) {
      this.currentOrder = order;
      this.form = {
        content: '',
        status: '',
        goodId: order.goodId,
        userId: order.userId
      };
      this.evaluateDialogVisible = true;
    },

    // 提交评价
    async submitEvaluation() {
      try {
        this.isSubmittingEvaluation = true;

        // 更新订单状态
        await updateInfo({
          ...this.currentOrder,
          status: '3' // 标记为已评价
        });

        // 提交评价内容
        await addEvalute(this.form);

        this.$modal.msgSuccess("评价提交成功");
        this.evaluateDialogVisible = false;
        this.loadOrders();
      } catch (error) {
        console.error('评价提交失败:', error);
        this.$modal.msgError("评价提交失败");
      } finally {
        this.isSubmittingEvaluation = false;
      }
    },

    // 打开寄回对话框
    openReturnDialog(order) {
      this.currentOrder = order;
      this.returnForm.expressId = '';
      this.returnDialogVisible = true;
    },

    // 提交寄回信息
    async submitReturn() {
      this.$refs.returnFormRef.validate(valid => {
        if (valid) {
          // 验证通过，继续提交逻辑
          try {
            this.isSubmittingReturn = true;
            updateInfo({
              ...this.currentOrder,
              userExpressId: this.returnForm.expressId
            });
            this.$modal.msgSuccess("寄回信息已提交");
            this.returnDialogVisible = false;
            this.loadOrders();
          } catch (error) {
            console.error('寄回信息提交失败:', error);
            this.$modal.msgError("寄回信息提交失败");
          } finally {
            this.isSubmittingReturn = false;
          }
        } else {
          // 验证不通过，给出提示
          this.$modal.msgError("请检查输入的快递单号，确保符合规则");
          return;
        }
      });
    },

    // 格式化时间显示
    formatTime(timestamp) {
      if (!timestamp) return '--';
      const date = new Date(timestamp);
      return date.toLocaleString('zh-CN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
      });
    },

    // 状态文本映射
    getStatusText(status) {
      const statusMap = {
        '0': '待发货',
        '1': '待收货',
        '2': '已完成',
        '3': '已评价'
      };
      return statusMap[status] || '未知状态';
    },

    // 状态样式类
    getStatusClass(status) {
      return `status-${['pending', 'shipping', 'completed', 'reviewed'][status] || 'unknown'}`;
    }
  }
};
</script>

<style scoped>
.order-page {
  max-width: 1200px;
  margin: 0 auto;
  padding: 24px;
}

.order-container {
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
  margin-bottom: 20px;
  transition: transform 0.2s ease;
}

.order-container:hover {
  transform: translateY(-3px);
}

.order-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 24px;
  border-bottom: 1px solid #f0f0f0;
}

.order-meta {
  flex: 1;
}

.order-number {
  margin: 0;
  font-size: 16px;
  color: #1d1d1f;
}

.order-date {
  font-size: 12px;
  color: #86868b;
  display: block;
  margin-top: 4px;
}

.order-status {
  font-size: 12px;
  padding: 6px 12px;
  border-radius: 8px;
  font-weight: 500;
}

.status-pending { background: #fef3c7; color: #d97706; }
.status-shipping { background: #dbeafe; color: #2563eb; }
.status-completed { background: #dcfce7; color: #16a34a; }
.status-reviewed { background: #f0f4ff; color: #2d5afa; }

.return-tag {
  font-size: 0.9em;
  margin-left: 8px;
}

.detail-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 12px;
  padding: 16px 24px;
}

.detail-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 13px;
  padding: 8px 0;
}

.label {
  color: #86868b;
}

.value {
  color: #1d1d1f;
  font-weight: 500;
}

.price {
  color: #ff3b30;
  font-weight: 600;
}

.return-express {
  color: #2d5afa;
}

.action-buttons {
  padding: 16px 24px;
  border-top: 1px solid #f0f0f0;
  text-align: right;
}

.rating-good { color: #34c759; }
.rating-neutral { color: #ff9500; }
.rating-bad { color: #ff3b30; }

@media (max-width: 768px) {
  .order-header {
    flex-direction: column;
    align-items: flex-start;
  }

  .order-status {
    margin-top: 8px;
  }

  .action-buttons {
    display: flex;
    flex-wrap: wrap;
    gap: 8px;

    > * {
      flex: 1;
      margin: 0;
    }
  }
}

</style>
