<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="挂号单号" prop="oId">
        <el-input
          v-model="queryParams.oId"
          placeholder="请输入挂号单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收费订单号" prop="coId">
        <el-input
          v-model="queryParams.coId"
          placeholder="请输入收费订单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退费总金额" prop="roTotalAmonut">
        <el-input
          v-model="queryParams.roTotalAmonut"
          placeholder="请输入退费总金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退费订单的创建时间" prop="roCreateTime">
        <el-date-picker clearable
          v-model="queryParams.roCreateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择退费订单的创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="退费订单的修改时间" prop="roUpdateTime">
        <el-date-picker clearable
          v-model="queryParams.roUpdateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择退费订单的修改时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="逻辑删除(0:未删除,1:已删除)" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入逻辑删除(0:未删除,1:已删除)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:refund:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:refund:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:refund:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:refund:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="refundList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="退费订单单号" align="center" prop="roId" />
      <el-table-column label="挂号单号" align="center" prop="oId" />
      <el-table-column label="收费订单号" align="center" prop="coId" />
      <el-table-column label="退费总金额" align="center" prop="roTotalAmonut" />
      <el-table-column label="订单状态" align="center" prop="roStatus" />
      <el-table-column label="退费订单的创建时间" align="center" prop="roCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.roCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="退费订单的修改时间" align="center" prop="roUpdateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.roUpdateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="逻辑删除(0:未删除,1:已删除)" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:refund:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:refund:remove']"
          >删除</el-button>
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="挂号单号" prop="oId">
          <el-input v-model="form.oId" placeholder="请输入挂号单号" />
        </el-form-item>
        <el-form-item label="收费订单号" prop="coId">
          <el-input v-model="form.coId" placeholder="请输入收费订单号" />
        </el-form-item>
        <el-form-item label="退费总金额" prop="roTotalAmonut">
          <el-input v-model="form.roTotalAmonut" placeholder="请输入退费总金额" />
        </el-form-item>
        <el-form-item label="退费订单的创建时间" prop="roCreateTime">
          <el-date-picker clearable
            v-model="form.roCreateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择退费订单的创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="退费订单的修改时间" prop="roUpdateTime">
          <el-date-picker clearable
            v-model="form.roUpdateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择退费订单的修改时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="逻辑删除(0:未删除,1:已删除)" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入逻辑删除(0:未删除,1:已删除)" />
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
import { listRefund, getRefund, delRefund, addRefund, updateRefund } from "@/api/system/refund";

export default {
  name: "Refund",
  data() {
    return {
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
      // 【请填写功能名称】表格数据
      refundList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        oId: null,
        coId: null,
        roTotalAmonut: null,
        roStatus: null,
        roCreateTime: null,
        roUpdateTime: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listRefund(this.queryParams).then(response => {
        this.refundList = response.rows;
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
        roId: null,
        oId: null,
        coId: null,
        roTotalAmonut: null,
        roStatus: null,
        roCreateTime: null,
        roUpdateTime: null,
        isDeleted: null
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
      this.ids = selection.map(item => item.roId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加【请填写功能名称】";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const roId = row.roId || this.ids
      getRefund(roId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改【请填写功能名称】";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.roId != null) {
            updateRefund(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRefund(this.form).then(response => {
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
      const roIds = row.roId || this.ids;
      this.$modal.confirm('是否确认删除【请填写功能名称】编号为"' + roIds + '"的数据项？').then(function() {
        return delRefund(roIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/refund/export', {
        ...this.queryParams
      }, `refund_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
