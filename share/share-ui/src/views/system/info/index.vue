<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品id" prop="goodId">
        <el-input
            v-model="queryParams.goodId"
            placeholder="请输入商品id"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商家" prop="busName">
        <el-input
            v-model="queryParams.busName"
            placeholder="请输入商家"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input
            v-model="queryParams.userId"
            placeholder="请输入用户id"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择订单状态" clearable>
          <el-option
              v-for="dict in dict.type.status_order"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="id" align="center" prop="id" width="60"   />
      <el-table-column label="订单id" align="center" prop="orderId"/>
      <el-table-column label="商品id" align="center" prop="goodId"/>
      <el-table-column label="商家" align="center" prop="busName"/>
      <el-table-column label="用户id" align="center" prop="userId" width="60" />
      <el-table-column label="创建时间" align="center" prop="buildTime" width="95">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.buildTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="完成时间" align="center" prop="overTime" width="95">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.overTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column >
      <el-table-column label="订单状态" align="center" prop="status" width="150">
        <template slot-scope="scope" style="width: 100px"  >
          <dict-tag  size="small" :options="dict.type.status_order" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="发货时间" align="center" prop="sendTime" width="95">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.sendTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="快递单号" align="center" prop="expressId"/>
      <el-table-column label="租用时间" align="center" prop="time"/>
      <el-table-column label="地址" align="center" prop="address"/>
      <el-table-column label="价格" align="center" prop="price"/>
      <el-table-column label="用户返回单号
" align="center" prop="userExpressId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:info:edit']"
          >发货
          </el-button>
          <el-button
              size="mini"
              type="text"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:info:remove']"
          >取消订单
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改订单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单id" prop="orderId">
          <el-input :disabled="true" v-model="form.orderId" placeholder="请输入订单id"/>
        </el-form-item>
        <el-form-item label="商品id" prop="goodId">
          <el-input :disabled="true" v-model="form.goodId" placeholder="请输入商品id"/>
        </el-form-item>
        <el-form-item label="商家" prop="busName">
          <el-input :disabled="true" v-model="form.busName" placeholder="请输入商家"/>
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
          <el-input :disabled="true" v-model="form.userId" placeholder="请输入用户id"/>
        </el-form-item>
        <el-form-item label="创建时间" prop="buildTime">
          <el-date-picker :disabled="true" clearable
                          v-model="form.buildTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="完成时间" prop="overTime">
          <el-date-picker :disabled="true" clearable
                          v-model="form.overTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订单状态" prop="status">
          <el-select :disabled="true" v-model="form.status" placeholder="请选择订单状态">
            <el-option
                v-for="dict in dict.type.status_order"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发货时间" prop="sendTime">
          <el-date-picker clearable
                          v-model="form.sendTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择发货时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="快递单号" prop="expressId">
          <el-input v-model="form.expressId" placeholder="请输入快递单号"/>
        </el-form-item>
        <el-form-item label="租用时间" prop="time">
          <el-input :disabled="true" v-model="form.time" placeholder="请输入租用时间"/>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input :disabled="true" v-model="form.address" type="textarea" placeholder="请输入地址"/>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input :disabled="true" v-model="form.price" placeholder="请输入价格"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listInfo, getInfo, delInfo, addInfo, updateInfo} from "@/api/system/info";
import {getUserProfile} from "@/api/system/user";

export default {
  name: "Info",
  dicts: ['status_order'],
  data() {
    return {
      user: {},
      id: '',
      nickName: '',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 订单管理表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodId: null,
        busName: null,
        status: null,

      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getUser();


  },
  methods: {
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.id = this.user.userId;
        this.nickName = this.user.nickName;
        this.getList();
      });
    },
    /** 查询订单管理列表 */
    getList() {
      this.loading = true;
      if (this.id === 1) {
      } else {
        // 设置 userId 参数
        this.queryParams.busName = this.nickName;
      }
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        orderId: null,
        goodId: null,
        busName: null,
        userId: null,
        buildTime: null,
        overTime: null,
        status: null,
        sendTime: null,
        expressId: null,
        time: null,
        address: null,
        price: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "发货订单管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        this.form.status = "1";
        if (valid) {
          if (this.form.id != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("发货成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除订单管理编号为"' + ids + '"的数据项？').then(function () {
        return delInfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
