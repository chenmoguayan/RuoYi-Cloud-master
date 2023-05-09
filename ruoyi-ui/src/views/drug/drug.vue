<template>
  <div>
    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" :rules="rules" label-width="100px" size="medium">
        <el-col :span="12">
          <el-form-item label="挂号单ID" prop="mobile">
            <el-input v-model="formData.mobile" :maxlength="30" :style="{width: '100%'}" clearable placeholder="请输入挂号单ID"
                      prefix-icon='el-icon-mobile' show-word-limit></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item>
            <el-button icon="el-icon-search" type="primary" @click="submitForm"> 查询</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item>
            <el-button icon="el-icon-search" type="primary"> 读取IC卡</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24" v-if="spanboolean">
          <el-form-item align="center" size="large">
            <span>请输入正确的挂号单ID查询</span>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>

    <div id="haha">
      <el-descriptions v-if="descboolean" title="挂号单信息">
        <el-descriptions-item label="挂号单号" >{{ this.descData.oId }}</el-descriptions-item>
        <el-descriptions-item label="医生姓名" >{{ this.descData.oPipno }}</el-descriptions-item>
        <el-descriptions-item label="科室名称" >{{this.descData.oType}}</el-descriptions-item>
        <el-descriptions-item label="患者姓名" >{{ this.descData.hsPatient.pName }}</el-descriptions-item>
        <el-descriptions-item label="就诊时间" >{{ this.descData.updateTime }}</el-descriptions-item>
        <el-descriptions-item label="主诉" >{{ this.descData.hsPatient.pAge }}</el-descriptions-item>
        <el-descriptions-item label="诊断信息" >{{ this.descData.oStatus }}</el-descriptions-item>
        <el-descriptions-item label="医生建议" >{{this.descData.oType}}</el-descriptions-item>
      </el-descriptions>
      <el-row v-if="formorder">
        <el-button type="primary" @click="checked()">全选</el-button>
        <el-button type="success" @click="unchecked()">取消全选</el-button>
        <el-button type="danger" @click="cash()">确定发药</el-button>
<!--        <el-form>-->
<!--          <el-form-item label="订单总额：" style="color: red">-->
<!--            <span v-text="'￥：'+ddze"></span>-->
<!--          </el-form-item>-->
<!--        </el-form>-->
      </el-row>
    </div>

    <div id="haha" >
      <el-collapse  v-if="formorder" v-model="activeNames" @change="handleChange">
<!--        <el-collapse-item :title="'【检查处方】【处方总额】:￥'+checkMoney" name="1">-->
<!--          <el-table ref="multipleSelection1" @selection-change="handleSelectionChange" :data="tableDataP" style="width: 100%">-->
<!--            <el-table-column type="selection" width="55" align="center">-->
<!--            </el-table-column>-->
<!--            <el-table-column label="序号" prop="prId" align="center">-->
<!--            </el-table-column>-->
<!--            <el-table-column label="检查名称" prop="inspectionItem" align="center">-->
<!--            </el-table-column>-->
<!--            <el-table-column label="数量" prop="number" align="center">-->
<!--            </el-table-column>-->
<!--            <el-table-column label="单价(元)" align="center" prop="unitPrice">-->
<!--            </el-table-column>-->
<!--            <el-table-column label="金额(元)" align="center" prop="total">-->
<!--            </el-table-column>-->
<!--            <el-table-column label="支付状态" prop="payType" align="center">-->
<!--            </el-table-column>-->
<!--          </el-table>-->
<!--        </el-collapse-item>-->
        <el-collapse-item :title="'【药品处方】【处方总额】:￥'+drugMoney" name="2">
          <el-table ref="multipleSelection2" @selection-change="handleSelectionChange" :data="tableDataR" style="width: 100%">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column label="序号" align="center" prop="prId">
            </el-table-column>
            <el-table-column label="药品名称" align="center" prop="medicineName">
            </el-table-column>
            <el-table-column label="数量" align="center" prop="number">
            </el-table-column>
            <el-table-column label="单价(元)" align="center" prop="price">
            </el-table-column>
            <el-table-column label="金额(元)" align="center" prop="total">
            </el-table-column>
            <el-table-column label="支付状态" align="center" prop="payType">
            </el-table-column>
          </el-table>
        </el-collapse-item>
      </el-collapse>
    </div>  </div>

</template>

<script>
import ScrollPane from "@/layout/components/TagsView/ScrollPane";
import PanelGroup from "@/views/dashboard/PanelGroup";
import {getPay} from "@/api/chargeable/chargeable";
import OrderApi from "@/api/visit/order";

export default {
  components:{ScrollPane, PanelGroup},

  data() {
    return {
      activeNames: ['2'],// 折叠面板默认选中第二个
      spanboolean: true,
      descboolean: false,
      formorder: false,
      open: false,
      ddze: 100,// 订单总额
      drugMoney: 66, // 药品金额
      checkMoney: 12, // 检查金额
      coId: 0,
      oId: 0,
      formData: {
        mobile: '',
      },
      chargeableData: {
        roId: undefined,
        oId: 0,
        coId: 0,
        roTotalAmonut: undefined,
        roStatus: undefined,
      },
      descData: {
        hsPatient:{
          pName:undefined,
          pAge:undefined,
        },
        oStatus:undefined,
        oType:undefined,
        oPipno:undefined,
      },
      rules: {
        mobile: [{
          required: true,
          message: '请输入挂号单号',
          trigger: 'blur'
        }, {
          // pattern: /^d{30}$/,
          // message: '手机号格式错误',
          // trigger: 'blur'
        }],
      },
      tableData:[],
      tableDataUpdated: {
        id:undefined,
        stock:undefined,
      },
      chufangUpdated: {
        prId:undefined,
        payType:undefined,
      },
      // tableDataP: [],
      tableDataR: [],
      multipleSelection1: [], // 选择后的数据
      multipleSelection2: [], // 选择后的数据
    }
  },
  computed: {},
  watch: {},
  created() {
    this.ddze = this.drugMoney+this.checkMoney;
  },
  updated() {
    this.ddze = this.drugMoney+this.checkMoney;
  },
  mounted() {
  },
  methods: {
    fayao(){
      this.tableDataR.forEach(protect =>{
        this.cleanUpdate();
        this.cleaerUpdate();
        protect.payType = '已发药';
        OrderApi.getMedicine(protect.cId).then(response =>{
          this.tableDataUpdated.id = response.data.id;
          this.tableDataUpdated.stock = response.data.stock - protect.number;
          OrderApi.updateDrug(this.tableDataUpdated).then(response =>{
            console.log(response);
          })
        })
        this.chufangUpdated.prId = protect.prId;
        this.chufangUpdated.payType = protect.payType;
        OrderApi.edit(this.chufangUpdated).then(response =>{
          console.log(response)
        })
      })
    },
    cleaerUpdate() {
      this.tableDataUpdated.prId = undefined
      this.tableDataUpdated.payType = undefined
    },
    cleanUpdate() {
      this.tableDataUpdated.id = ""
      this.tableDataUpdated.stock = 0
    },
    /**
     * el-table点击选择操作
     */
    handleSelectionChange(val){
      // if (this.activeNames == '1'){
      //   this.multipleSelection1 = val;
      // }
      if (this.activeNames == '2'){
        this.multipleSelection2 = val;
      }
    },
    /**
     * 全选
     */
    checked(){
      // if(this.activeNames == 1){
      //   this.$refs.multipleSelection1.clearSelection()
      //   this.$refs.multipleSelection1.toggleAllSelection()
      // }
      if(this.activeNames == 2){
        this.$refs.multipleSelection2.clearSelection()
        this.$refs.multipleSelection2.toggleAllSelection()
      }

    },
    /**
     * 取消全选(反选)
     */
    unchecked(){
      // if (this.activeNames == '1'){
      //   console.log(this.$refs.multipleSelection1.selection)
      //   this.$refs.multipleSelection1.tableData.forEach(item=>{
      //     this.$refs.multipleSelection1.toggleRowSelection(item,!(this.$refs.multipleSelection1.selection.includes(item)))
      //   })
      // }
      if (this.activeNames == '2'){
        console.log(this.$refs.multipleSelection2.selection)
        this.$refs.multipleSelection2.tableData.forEach(item=>{
          this.$refs.multipleSelection2.toggleRowSelection(item,!(this.$refs.multipleSelection2.selection.includes(item)))
        })
      }

    },
    /**
     * 折叠面板选中属性打印
     * @param val
     */
    handleChange(val) {
      console.log(val);
    },
    /**
     * 发药
     */
    cash(){
      this.chargeableData.coId = this.coId;
      this.chargeableData.oId = this.oId;
      this.chargeableData.roTotalAmonut = this.ddze;
      // this.chargeableData.roStatus = 0;
      console.log(this.chargeableData)
      if (this.chargeableData.roStatus === undefined) {
        this.$confirm('即将确认发药, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.chargeableData.roStatus = 1;
          this.fayao();
          this.$message({
            type: 'success',
            message: '发药成功!'
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消发药'
          });
          this.clearData();
        });
      }else if (this.chargeableData.roStatus === 1) {
        this.$message({
          type: 'info',
          message: '已经发药，请勿重复发药'
        })
      }



    },

    /**
     * 根据挂号单ID查询挂号单信息
     *
     */
    submitForm() {
      if (this.formData.mobile !== '') {
        this.spanboolean = false;
        this.descboolean = true;
        this.formorder = true;
        OrderApi.getOrderList(this.formData.mobile).then(response =>{
          console.log(response)
          this.descData = response.rows[0];
        });
        getPay(this.formData.mobile).then(response1 =>{
          console.log(response1)
          this.coId = response1.data.coId;
          this.oId = response1.data.oId
        });
        OrderApi.queryByOid(this.formData.mobile).then(response =>{
          console.log(response);
          this.tableData = response.rows;
          for (let protect of this.tableData) {
            // if (protect.prType == "检查"){
            //   OrderApi.getProtect(protect.cId).then(response =>{
            //     this.$set(protect,'inspectionItem',response.data.data.inspectionItem);
            //     this.$set(protect,'unitPrice',response.data.data.unitPrice);
            //     this.$set(protect,'total',response.data.data.unitPrice * protect.number);
            //     this.checkMoney += response.data.data.unitPrice*protect.number;
            //   })
            //   this.tableDataP.push(protect);
            // }
            if (protect.prType == "药品"){
              OrderApi.getMedicine(protect.cId).then(response =>{
                this.$set(protect,'medicineName',response.data.medicineName);
                this.$set(protect,'price',response.data.price);
                this.$set(protect,'total',response.data.price * protect.number);
                this.drugMoney += response.data.price * protect.number;
              })
              this.tableDataR.push(protect);
            }
          }
          console.log(this.tableDataR);
        });
      }else if (this.formData.mobile === ''){
        this.spanboolean = true;
        this.descboolean = false;
        this.formorder = false;
      }
    },

    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}

</script>

<style scoped>
#haha {
  padding-left: 30px;
  padding-top: 30px;
}
</style>
