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
      <el-form-item label="患者姓名" prop="pName">
        <el-input
          v-model="queryParams.pName"
          placeholder="请输入患者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

<!--
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['refund:pay:add']"
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
          v-hasPermi="['refund:pay:edit']"
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
          v-hasPermi="['refund:pay:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['refund:pay:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
-->

    <el-table v-loading="loading" :data="payList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="退费订单单号" align="center" prop="roId" />
      <el-table-column label="挂号单号" align="center" prop="oId" />
      <el-table-column label="收费订单号" align="center" prop="coId" />
      <el-table-column label="患者姓名" align="center" prop="pName"/>
      <el-table-column label="退费总金额" align="center" prop="roTotalAmonut" />
      <el-table-column label="订单状态" align="center" prop="roStatus" >
        <template v-slot="payList">
          {{ payList.row.roStatus === 1 ?'已退费':'未退费' }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="payList">
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(payList.row)"
            v-hasPermi="['refund:pay:edit']"
          >查看详情</el-button>
<!--          <br>-->
<!--          <el-button  size="mini" type="primary" @click="cash(payList.row.roId)">退费</el-button>-->
        </template>
      </el-table-column>
    </el-table>
<!--   分页组件-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改退费订单信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="60%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="挂号单号" prop="roId">
          <el-input v-model="form.roId" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="挂号单号" prop="oId">
          <el-input v-model="form.oId" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="收费订单号" prop="coId">
          <el-input v-model="form.coId" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="患者姓名" prop="pName">
          <el-input v-model="form.pName" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="总金额" prop="roTotalAmonut">
          <el-input v-model="form.roTotalAmonut" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="挂号单号" prop="createTime">
          <el-input v-model="form.createTime" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="挂号单号" prop="updateTime">
          <el-input v-model="form.updateTime" show-word-limit readonly />
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { listPay, getPay, delPay, addPay, updatePay } from "@/api/refund/refund";

export default {
  name: "Pay",
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
      // 退费订单信息表格数据
      payList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      chargeableData: {
        roId:undefined,
        oId:undefined,
        pName:undefined,
        coTotalAmount:undefined,
        coPayType:undefined,
        coStatus:undefined,
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        oId: null,
        coId: null,
        pName: null,
        roTotalAmonut: null,
        roStatus: null,
      },
      // 表单参数
      form: {
        roId: null,
        oId: null,
        coId: null,
        coTotalAmount: null,
        pName: null,
        coPayType: null,
        coStatus: null,
        createTime: null,
      },
      // 表单校验
      rules: {
        oId: [
          { required: true, message: "挂号单号不能为空", trigger: "blur" }
        ],
        coId: [
          { required: true, message: "收费订单号不能为空", trigger: "blur" }
        ],
        roTotalAmonut: [
          { required: true, message: "退费总金额不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询退费订单信息列表 */
    getList() {
      this.loading = true;
      listPay(this.queryParams).then(response => {
        console.log(response);
        this.payList = response.rows;
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
        createTime: null,
        updateTime: null,
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

    /**
     * 现金支付
     */
    cash(roId){
      this.clearData();
      console.log(roId)
      this.$confirm('即将退费, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.chargeableData.roId = roId;
        this.chargeableData.roStatus = 1;
        updatePay(this.chargeableData).then(response =>{
          this.$message({
            type: 'success',
            message: '退费成功!'
          });
        })
        this.clearData();
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退费'
        });
        this.clearData();
      });
    },

    /** 查看详情按钮操作 */
    handleUpdate(row) {
      this.reset();
      const roId = row.roId || this.ids
      getPay(roId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "退费订单信息详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.roId != null) {
            updatePay(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPay(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    /** 导出按钮操作 */
    handleExport() {
      this.download('refund/pay/export', {
        ...this.queryParams
      }, `pay_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
