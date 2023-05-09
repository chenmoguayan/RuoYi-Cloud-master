<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="收费订单号" prop="coId">
        <el-input
          v-model="queryParams.coId"
          placeholder="请输入收费订单号"
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


    <el-table v-loading="loading" :data="payList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="收费订单号" align="center" prop="coId" />
      <el-table-column label="挂号单号" align="center" prop="oId" />
      <el-table-column label="患者姓名" align="center" prop="pName"/>
      <el-table-column label="总金额" align="center" prop="coTotalAmount" />
      <el-table-column label="支付类型" align="center" prop="coPayType">
        <template v-slot="payList">
          {{payList.row.coPayType===0?'现金':'支付宝'}}
        </template>
      </el-table-column>
      <el-table-column label="支付状态" align="center" prop="coStatus">
        <template v-slot="payList">
          {{ payList.row.coStatus===1?'已支付':'未支付'}}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="payList">
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(payList.row)"
            v-hasPermi="['chargeable:pay:edit']"
          >查看详情</el-button>
          <br>
          <el-button  size="mini" type="primary" @click="cash(payList.row.coId)">现金</el-button>
          <el-button  size="mini" type="primary" @click="openBox(payList.row.coId)">支付宝</el-button>
        </template>
      </el-table-column>
    </el-table>
<!--    分页组件-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!--    支付宝弹框-->
    <el-dialog :visible.sync="openzfb" style="text-align: center">
      <el-descriptions style="width: 100%">
        <el-descriptions-item label="订单号" v-model="form.coId">{{ form.coId }}</el-descriptions-item>
        <el-descriptions-item label="总金额" v-model="form.coTotalAmount">{{ form.coTotalAmount }}</el-descriptions-item>
      </el-descriptions>
      <el-empty image="https://lhj2001.oss-cn-shanghai.aliyuncs.com/zfb.jpg" description="请使用支付宝扫码支付"></el-empty>
    </el-dialog>


    <!-- 详情收费订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="60%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="挂号单号" prop="oId">
          <el-input v-model="form.oId" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="收费单号" prop="coId">
          <el-input v-model="form.coId" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="患者姓名" prop="pName">
          <el-input v-model="form.pName" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="总金额" prop="coTotalAmount">
          <el-input v-model="form.coTotalAmount" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="支付状态" prop="coPayType">
          <el-input v-model="form.coPayType===0?'现金':'支付宝'" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-input v-model="form.createTime" show-word-limit readonly />
        </el-form-item>
        <el-form-item label="更新时间" prop="updateTime">
          <el-input v-model="form.updateTime" show-word-limit readonly />
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { listPay, getPay, delPay, addPay, updatePay } from "@/api/chargeable/chargeable";


export default {
  name: "Pay",
  data() {
    return {
      // form:[],
      // 显示支付按钮
      payShow:false,
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
      // 收费订单表格数据
      payList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openzfb: false,
      // 建表数据
      chargeableData: {
        coId:undefined,
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
        coId: null,
        oId: null,
        pName: null,
        coTotalAmount: null,
        coPayType: null,
        coStatus: null,
      },
      // 表单参数
      form: {
        coId: null,
        oId: null,
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
        coTotalAmount: [
          { required: true, message: "总费用不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    clearData(){
      this.chargeableData.coId=undefined;
      this.chargeableData.oId=undefined;
      this.chargeableData.coTotalAmount=undefined;
      this.chargeableData.coPayType=undefined;
      this.chargeableData.coStatus=undefined
    },
    /**
     * 现金支付
     */
    cash(coId){
      this.clearData();
      console.log(coId)
      this.$confirm('将使用现金支付, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.chargeableData.coId = coId;
        this.chargeableData.coPayType = 1;
        this.chargeableData.coStatus = 1;
        console.log(this.chargeableData);
        updatePay(this.chargeableData).then(response =>{
          this.$message({
            type: 'success',
            message: '支付成功!'
          });
        })

        this.clearData()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消支付'
        });
        this.clearData()
      });


    },
    /**
     * 支付宝支付
     * 弹框
     */
    openBox(coId){
      this.clearData();
      this.openzfb = true;
      this.chargeableData.coId = coId;
      this.chargeableData.coPayType = 0;
      this.chargeableData.coStatus = 1;
      updatePay(this.chargeableData).then(response =>{
        this.$modal.msgSuccess("支付成功");
        this.clearData()
      })
    },
    /** 查询收费订单列表 */
    getList() {
      this.loading = true;
      listPay(this.queryParams).then(response => {
        this.payList = response.rows;
        console.log(this.payList);
        this.total = response.total;
        this.loading = false;
        this.payList.forEach(obj => {
          this.$set(obj,'payShow',false);
          if (obj.coStatus === 0){
            obj.payShow=true;
          }
        });
        console.log(this.payShow);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      // this.form = {
      //   coId: null,
      //   oId: null,
      //   pName: null,
      //   coTotalAmount: null,
      //   coPayType: null,
      //   coStatus: null,
      //   createTime: null,
      //   updateTime: null,
      //   isDeleted: null
      // };
      this.form=[],
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
      this.ids = selection.map(item => item.coId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
/*    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加收费订单";
    },*/
    /** 详情按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.open = true;
      console.log(row)
      this.form = row;
      console.log(this.form)
      this.title = "收费订单详情";
      // const coId = row.roId || this.ids
      // console.log(coId)
      // getPay(coId).then(response => {
      //   console.log(response);
      //   this.form = response.data;
      //   console.log(this.form);
      // });
    },
/*    /!** 提交按钮 *!/
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.coId != null) {
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
    /!** 删除按钮操作 *!/
    handleDelete(row) {
      const coIds = row.coId || this.ids;
      this.$modal.confirm('是否确认删除收费订单编号为"' + coIds + '"的数据项？').then(function() {
        return delPay(coIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /!** 导出按钮操作 *!/
    handleExport() {
      this.download('chargeable/pay/export', {
        ...this.queryParams
      }, `pay_${new Date().getTime()}.xlsx`)
    }*/
  }
};
</script>
