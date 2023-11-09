<template>
  <div>
    <div>退款售后</div>
    <!--------------------------------------- 数据搜索框 --------------------------------------->
    <el-card shadow="always" style="margin-bottom: 10px">
    <div style="margin-bottom: 5px;width: 75%;">
      <el-form
        ref="form"
        :model="afterSaleDTO"
        size="small"
        label-width="80px"
        style="margin-top: 23px"
      >
        <el-row :gutter="30">
          <el-col :span="6">
            <el-form-item label="订单号">
              <el-input v-model="afterSaleDTO.ddId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="售后单号">
              <el-input v-model="afterSaleDTO.asId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="所属店铺">
              <el-select v-model="afterSaleDTO.mdName" placeholder="请选择">
                <el-option key=null label="请选择" value=null />
                <el-option
                  v-for="af in shops"
                  :key="af.asId"
                  :label="af.asType"
                  :value="af.asId"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="售后类型">
              <el-select v-model="afterSaleDTO.asType" placeholder="请选择">
                <el-option key=null label="请选择" value=null />
                <el-option
                  v-for="af in afterSaleTypes"
                  :key="af.id"
                  :label="af.asType"
                  :value="af.asType"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="14">
          <el-col :span="6">
            <el-form-item label="售后状态">
              <el-select v-model="afterSaleDTO.asStatus" placeholder="请选择">
                <el-option key=null label="请选择" value=null />
                <el-option
                  v-for="af in afterSaleStatus"
                  :key="af.id"
                  :label="af.asStatus"
                  :value="af.asStatusd"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="申请时间">
              <el-date-picker
                v-model="ArrayDate"
                type="daterange"
                align="right"
                unlink-panels
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="2" style="margin-left: 15px">
            <div>
              <button
                type="button"
                size="small"
                @click="search()"
                class="css-button-rounded--blue"
              >
                <i class="el-icon-search"></i>&nbsp;查询
              </button>
            </div>
          </el-col>
          <el-col :span="2">
            <div>
              <button
                type="button"
                size="small"
                @click="reset()"
                class="css-button-rounded--green"
              >
                <i class="el-icon-refresh"></i>&nbsp;重置
              </button>
            </div>
          </el-col>
        </el-row>
      </el-form>
    </div>
    </el-card>
    <el-card shadow="always">
      <div>
      <!-------------------------------------- 表格数据 ---------------------------------------->
        <el-table
                border
                ref="multipleTable"
                :data="pageBean.beanList"
                tooltip-effect="dark"
                style="width: 100%"
                :default-sort="{ order: 'descending' }"
                :header-cell-style="{
                  background: '#F5F7FA',
                  'text-align': 'center',
                  color: '#606266',
                }"
                stripe
                highlight-current-row
                @selection-change="handleSelectionChange"
              >
                <el-table-column type="selection" width="55" class="myRedCheckBox">
                </el-table-column>
                <el-table-column
                  align="center"
                  prop="asId"
                  label="售后单号"
                  width="160"
                  show-overflow-tooltip
                  sortable
                  fixed
                >
                  <template slot-scope="scope">{{ scope.row.asId }}</template>
                </el-table-column>

                <el-table-column
                  align="center"
                  prop="ddId"
                  label="对应订单号"
                  width="200"
                  show-overflow-tooltip
                  sortable
                  fixed
                >
                  <template slot-scope="scope">{{ scope.row.ddId }}</template>
                </el-table-column>

                <el-table-column
                  align="center"
                  prop="mdName"
                  label="所属店铺"
                  width="120"
                  show-overflow-tooltip
                  sortable
                  fixed
                >
                  <template slot-scope="scope">{{ scope.row.mdName }}</template>
                </el-table-column>

                <el-table-column
                  align="center"
                  prop="ddNum"
                  label="售后数量"
                  width="120"
                  show-overflow-tooltip
                  sortable
                >
                  <template slot-scope="scope">{{ scope.row.ddNum }}</template>
                </el-table-column>

                <el-table-column
                  align="center"
                  prop="allMoney"
                  label="退款金额"
                  width="135"
                  show-overflow-tooltip
                >
                  <template slot-scope="scope">{{ scope.row.spPrice *  scope.row.ddNum }}</template>
                </el-table-column>

                <el-table-column
                  align="center"
                  label="买家"
                  prop="gkName"
                  width="140"
                  show-overflow-tooltip
                >
                  <template slot-scope="scope">{{ scope.row.gkName }}</template>
                </el-table-column>

                <el-table-column
                  align="center"
                  prop="asType"
                  label="售后类型"
                  width="130"
                  :filters="[
                    { text: '退货退款', value: '退货退款' },
                    { text: '换货', value: '换货' },
                  ]"
                  :filter-method="filterAsType"
                  filter-placement="bottom-end"
                >
                  <template slot-scope="scope">
                    <el-tag
                      :type="scope.row.asType === '换货' ? 'primary' : 'warning'"
                      disable-transitions
                      >{{ scope.row.asType }}</el-tag
                    >
                  </template>
                </el-table-column>

                <el-table-column
                  align="center"  
                  prop="sqDate"
                  label="申请时间"
                  width="160"
                  show-overflow-tooltip
                  sortable
                >
                  <template slot-scope="scope">{{ scope.row.sqDate }}</template>
                </el-table-column>

                <el-table-column
                  align="center"
                  prop="asStatus"
                  label="售后状态"
                  width="130"
                  :filters="[
                    { text: '待审核', value: '待审核' },
                    { text: '已驳回', value: '已驳回' },
                    { text: '退货中', value: '退货中' },
                    { text: '重新发货中', value: '重新发货中' },
                    { text: '拦截物流中', value: '拦截物流中' },
                    { text: '收到退货', value: '收到退货' },
                    { text: '买家已收货', value: '买家已收货' },
                    { text: '已拦截物流', value: '已拦截物流' },
                    { text: '已完成', value: '已完成' },
                  ]"
                  :filter-method="filterStatus"
                  filter-placement="bottom-end"
                >
                  <template slot-scope="scope">
                    <el-tag
                      :type="scope.row.asStatus === '待审核' ? 'primary' : scope.row.asStatus === '已驳回' ? 'warning' : 'primary'"
                      disable-transitions
                      >{{ scope.row.asStatus }}</el-tag
                    >
                  </template>
                </el-table-column>

                <el-table-column label="操作" fixed="right" align="center">
                  <template slot-scope="scope">
                    <button size="small" plain type="button" @click="afterSaleDetail(scope.$index, scope.row)"
                      class="css-button-fully-rounded--yellow">
                      <i class="el-icon-tickets"></i>&nbsp;详情
                    </button>
                  </template>
                </el-table-column>

        </el-table>
      </div>
      <!-------------------------------------- 分页标签 ----------------------------------------->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageBean.pageIndex"
        :page-sizes="[1, 2, 3, 10]"
        :page-size="pageBean.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageBean.totalRecord"
      >
      </el-pagination>
    </el-card>
    <!--------------------------------------- 详情对话框 --------------------------------------->
    <el-dialog :visible.sync="dialogVisible" width="60%" :before-close="handleClose">
    <h3 style="text-align: left;">订单详情</h3><br>
    <el-steps :active="steps" align-center>
      <el-step title="买家下单" description="获取下单时间" icon="el-icon-sold-out"></el-step>
      <el-step title="付款成功" description="获取付款时间" icon="el-icon-check"></el-step>
      <el-step title="商品出库" description="商品出库时间" icon="el-icon-truck"></el-step>
      <el-step title="确认收货" description="确认收货时间" icon="el-icon-circle-check"></el-step>
      <el-step :title="title5" description="确认收货时间" icon="el-icon-s-comment"></el-step>
      <el-step title="商家审核" description="确认收货时间" icon="el-icon-success"></el-step>
      <el-step :title="title7" description="确认收货时间" icon="el-icon-truck"></el-step>
      <el-step :title="title8" description="确认收货时间" icon="el-icon-s-claim"></el-step>
    </el-steps>
    <br><br>
    <div style="height: 100px;">
      <div style="margin-right: 10%; text-align: right;font-size: 25px ;color: #2D8CF0">{{ msg }}</div>
      <br>
      <!-- 同意按钮 -->
      <button type="button" size="small" @click="agree()" class="css-button-rounded--blue"
      style="margin-left: 74%" v-show="this.msg =='待审核'">
          <i class="el-icon-circle-check"></i>&nbsp;{{ msg1 }}
      </button>
      <!-- 驳回按钮 -->
      <button type="button" size="small" @click="reject()" class="css-button-arrow--red"
      v-show="this.msg =='待审核'">
          <i class="el-icon-circle-close"></i>&nbsp;{{ msg2 }}
      </button>
      
      <!-- 退货退款----退货中 -->
      <button type="button" size="small" @click="dealRequest1()" class="css-button-rounded--blue"
      style="margin-left: 74%" v-show="this.msg =='退货中'">
          <i class="el-icon-circle-check"></i>&nbsp;{{ msg3 }}
      </button>
      <!-- 换货-------重新发货中 -->
      <button type="button" size="small" @click="dealRequest1()" class="css-button-rounded--blue"
      style="margin-left: 74%" v-show="this.msg =='重新发货中'">
          <i class="el-icon-circle-check"></i>&nbsp;{{ msg3 }}
      </button>
      <!-- 仅退款-------拦截物流中 -->
      <button type="button" size="small" @click="dealRequest1()" class="css-button-rounded--blue"
      style="margin-left: 74%" v-show="this.msg =='拦截物流中'">
          <i class="el-icon-circle-check"></i>&nbsp;{{ msg3 }}
      </button>

      <!-- 退货退款----收到退货 -->
      <button type="button" size="small" @click="dealRequest2()" class="css-button-rounded--blue"
      style="margin-left: 74%" v-show="this.msg =='收到退货'">
          <i class="el-icon-circle-check"></i>&nbsp;{{ msg3 }}
      </button>
      <!-- 换货-------买家已收货 -->
      <button type="button" size="small" @click="dealRequest2()" class="css-button-rounded--blue"
      style="margin-left: 74%" v-show="this.msg =='买家已收货'">
          <i class="el-icon-circle-check"></i>&nbsp;{{ msg3 }}
      </button>
      <!-- 仅退款-------已拦截物流 -->
      <button type="button" size="small" @click="dealRequest2()" class="css-button-rounded--blue"
      style="margin-left: 74%" v-show="this.msg =='已拦截物流'">
          <i class="el-icon-circle-check"></i>&nbsp;{{ msg3 }}
      </button>

      <button type="button" size="small" @click="finAfterSale()" class="css-button-rounded--blue"
      style="margin-left: 74%" v-show="this.msg =='已完成'">
          <i class="el-icon-circle-check"></i>&nbsp;{{ msg4 }}
      </button>
    </div>
    <h3 style="text-align: left;">售后详情</h3><br>
    <el-descriptions class="margin-top" :column="3" direction="vertical">
        <el-descriptions-item label="售后订单">&nbsp;&nbsp;&nbsp;{{ afId }}</el-descriptions-item>
        <el-descriptions-item label="零售订单">&nbsp;&nbsp;&nbsp;{{ ddId }}</el-descriptions-item>
        <el-descriptions-item label="所属门店">&nbsp;&nbsp;&nbsp;{{ mdName }}</el-descriptions-item>
        <el-descriptions-item label="交易状态">&nbsp;&nbsp;&nbsp;{{ orderType }}</el-descriptions-item>
        <el-descriptions-item label="买家昵称">&nbsp;&nbsp;&nbsp;{{ gkName }}</el-descriptions-item>
        <el-descriptions-item label="售后类型">&nbsp;&nbsp;&nbsp;{{ asType }}</el-descriptions-item>
        <el-descriptions-item label="申请数量">&nbsp;&nbsp;&nbsp;{{ ddNum }}</el-descriptions-item>
        <el-descriptions-item label="申请退款金额">&nbsp;&nbsp;&nbsp;￥{{ allMoney }}</el-descriptions-item>
        <el-descriptions-item label=""></el-descriptions-item><!-- 占位 -->
        <el-descriptions-item label="退款原因">&nbsp;&nbsp;&nbsp;{{ asReason }}</el-descriptions-item>
        <el-descriptions-item label=""></el-descriptions-item><!-- 占位 -->
        <el-descriptions-item label=""></el-descriptions-item><!-- 占位 -->
        <el-descriptions-item label="退款说明">&nbsp;&nbsp;&nbsp;{{ asExplain }}</el-descriptions-item>
    </el-descriptions>

    <h3 style="text-align: left;">进度跟踪</h3><br>
    <div style="height: 210px;">
      <el-steps direction="vertical" :active="activity">
        <el-step :title="tit1" :description="retialMsg1" icon="el-icon-time"></el-step>
        <el-step :title="tit2" :description="retialMsg2" v-show="tit1 != ''" icon="el-icon-time"></el-step>
        <el-step :title="tit3" :description="retialMsg3" v-show="tit2 != ''" icon="el-icon-time"></el-step>
        <el-step :title="tit4" :description="retialMsg4" v-show="tit3 != ''" icon="el-icon-time"></el-step>
      </el-steps>
    </div>

    <h3 style="text-align: left;">售后商品</h3><br>
    <el-table :data="detaildata" border style="width: 90%">
      <el-table-column prop="spName" label="商品名称" width="200"></el-table-column>
      <el-table-column prop="spImage" label="商品图片" width="200">
        <template slot-scope="scope">
              <el-button icon="el-icon-picture-outline" circle @click="getImage(scope.$index,scope.row)"></el-button>
          </template>
      </el-table-column>
      <el-table-column prop="spPrice" label="单价" width="180"></el-table-column>
      <el-table-column prop="ddNum" label="数量" width="180"></el-table-column>
      <el-table-column prop="allMoney" label="合计" width="180"></el-table-column>
    </el-table>
  </el-dialog>
  <!---------------------------------------显示图片的对话框------------------------------------->
  <el-dialog
			title="商品图片"
			:visible.sync="filed"
			width="30%">
		<el-image style="width: 300px; height: 300px" :src="path"></el-image>
		<span slot="footer" class="dialog-footer">
		    <el-button type="primary" @click="filed = false">关 闭</el-button>
		</span>
	</el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      filed:false,
      path:"",
      pageIndex: 1,
      pageSize: 10,
      addFlag: true,
      updateIndex: "",
      allPerson: [],
      pageBean: [],
      dialogVisible: false,
      afterSaleDTO:{
        afterSale:{asId:''},
        from:"",
        to:"",
      },
      shops:[

      ],
      afterSaleTypes:[
        {"id":1,"asType":"换货"},
        {"id":2,"asType":"退货退款"}
      ],
      afterSaleStatus:[
        {"id":1,"asStatus":"待审核"},
        {"id":2,"asStatus":"已驳回"},
        {"id":3,"asStatus":"退款中"},
        {"id":4,"asStatus":"已完成"},
      ],
      activity:1,
      ArrayDate:[],
      detaildata:[],
      title5:"申请退货退款",
      title7:"买家退货",
      title8:"退货退款完成",
      msg:"待审核",
      msg1:"同意",
      msg2:"驳回",
      msg3:"退款",
      msg4:"确定",
      afId:"",
      ddId:"",
      mdName:"",
      orderType:"交易中",
      gkName:"",
      asType:"",
      asStatus:"",
      ddNum:"",
      allMoney:null,
      asReason:"",
      asExplain:"",
      productMoney:null,
      tit1:"审核中",
      tit2:"",
      tit3:"",
      tit4:"",
      retialMsg1:"",
      retialMsg2:"",
      retialMsg3:"",
      retialMsg4:"",
      searchPerson: {},
      options: [],
      multipleSelection: [],
      selectedRows:[],
      currentRow: null,
      steps:0,
      
      };
      
  },
  created(){
    this.getAllAfterSale()
  },  
  methods: {
    //更改每页多少条信息
    handleSizeChange(val) {
        this.pageSize = val;
        this.getAllAfterSale();
    },
    //将当前页跳转到某一页axios
    handleCurrentChange(val) {
        this.pageIndex = val;
        this.getAllAfterSale();
    },
    //查询所有售后订单
    getAllAfterSale(){
        this.afterSaleDTO.from=this.ArrayDate[0];
        this.afterSaleDTO.to=this.ArrayDate[1];
        this.$axios
          .post(
            "http://localhost:8080/afterSale/get/" +this.pageIndex +"/" +this.pageSize,this.afterSaleDTO)
          .then((resp) => {
            this.pageBean = resp.data.data;
          });
    },
    //添加人员
    addOrUpdate() {
      if (this.addFlag == true) {
        this.allPerson.push(this.person);
        this.dialogVisible = false;
        this.$message({
          type: "success",
          message: "添加成功",
        });
        this.person = {};
      } else {
        // 修改
        this.allPerson.splice(this.updateIndex, 1, this.person);
        this.dialogVisible = false;
        this.addFlag;
        this.$message({
          type: "success",
          message: "修改成功",
        });
        this.person = {};
      }
    },
    //取消添加人员
    cancle() {
      this.dialogVisible = false;
      this.person = {};
    },
    //删除人员
    remove(index, row) {

    },
    //数据回显
    showUpdate(index, row) {
      this.addFlag = false;
      this.person = row;
      this.updateIndex = index;
      this.dialogVisible = true;
    },
    //用于保存选中的行
    handleSelectionChange(val) {
        this.multipleSelection = val;
    },
    //关闭对话框
    handleClose(done) {
          this.$confirm('确认关闭？')
            .then(_ => {
              done();
              this.detaildata=[];
            })
            .catch(_ => {});
    },
    //删除选中数据
    removeAll() {

    },
    //条件查询
    search() {

    },
    //搜索重置
    reset() {
      this.searchPerson = {};
      this.$message({
        type: "success",
        message: "重置成功",
      });
    },
    afterSales(){

    },
    //售后详情
    afterSaleDetail(index, row) {
      this.detaildata.push(row);
      this.detaildata[0].allMoney = this.detaildata[0].spPrice * this.detaildata[0].ddNum;
      this.dialogVisible=true;
      if(row.asType=="换货"){
          this.title5="申请换货"
          this.title7="买家换货"
          this.title8="换货完成"
        }else if(row.asType=="退货退款"){
          this.title5="申请退货退款"
          this.title7="买家退货"
          this.title8="退货退款完成"
        }else if(row.asType=="仅退款"){
          this.title5="申请仅退款"
          this.title7="拦截物流"
          this.title8="仅退款完成"
        }
      this.afId=row.asId;
      this.ddId=row.ddId;
      this.mdName=row.mdName; 
      this.orderType="交易中",
      this.gkName=row.gkName;
      this.asType=row.asType;
      this.asStatus=row.asStatus;
      this.ddNum=row.ddNum;
      this.allMoney=row.spPrice * row.ddNum;
      this.asReason=row.asReason;
      this.asExplain=row.asExplain;

      if(row.asStatus=="已驳回"){
        this.steps=4;this.msg="已驳回"
        this.tit2=row.finDate;
        this.activity=2;
        this.retialMsg2 = "条件不满足，驳回退款"

      }else if(row.asStatus=="待审核"){
        this.steps=5;this.msg="待审核";

      }else if(row.asStatus=="退货中"){
        this.msg3 = "退货"
        this.steps=6;this.msg="退货中";
        this.tit2=row.jcDate;
        this.retialMsg2 = "条件满足，同意退款退货"
        this.activity=2;
      }else if(row.asStatus=="重新发货中"){
        this.msg3 = "发货"
        this.steps=6;this.msg="重新发货中";
        this.tit2=row.jcDate;
        this.retialMsg2 = "条件满足，同意换货"
        this.activity=2;
      }else if(row.asStatus=="拦截物流中"){
        this.msg3 = "已拦截"
        this.steps=6;this.msg="拦截物流中";
        this.tit2=row.jcDate;
        this.retialMsg2 = "准备拦截物流，同意退款"
        this.activity=2;
      }else if(row.asStatus=="收到退货"){
        this.msg3 = "退款"
        this.steps=7;this.msg="收到退货";
        this.tit3=row.thDate;
        this.retialMsg3 = "等待买家退货"
        this.activity=3;
      }else if(row.asStatus=="买家已收货"){
        this.msg3 = "确定"
        this.steps=7;this.msg="买家已收货";
        this.tit3=row.thDate;
        this.retialMsg3 = "等待买家收货"
        this.activity=3;
      }else if(row.asStatus=="已拦截物流"){
        this.msg3 = "退款"
        this.steps=7;this.msg="已拦截物流";
        this.tit3=row.thDate;
        this.retialMsg3 = "即将退款"
        this.activity=3;
      }else if(row.asStatus=="已完成" && row.asType=="退货退款"){
        this.msg3 = "确认"
        this.steps=8;this.msg="已完成";
        this.tit2=row.jcDate;
        this.tit3=row.thDate;
        this.tit4=row.finDate;
        this.retialMsg2 = "条件满足，同意退款退货";
        this.retialMsg3 = "等待买家退货";
        this.retialMsg4 = "已完成售后服务";
        this.activity=4;
      }else if(row.asStatus=="已完成" && row.asType=="仅退款"){
        this.msg3 = "确认"
        this.steps=8;this.msg="已完成";
        this.tit2=row.jcDate;
        this.tit3=row.thDate;
        this.tit4=row.finDate;
        this.retialMsg2 = "准备拦截物流，同意退款";
        this.retialMsg3 = "即将退款";
        this.retialMsg4 = "已完成售后服务";
        this.activity=4;
      }else if(row.asStatus=="已完成" && row.asType=="换货"){
        this.msg3 = "确认"
        this.steps=8;this.msg="已完成";
        this.tit2=row.jcDate;
        this.tit3=row.thDate;
        this.tit4=row.finDate;
        this.retialMsg2 = "条件满足，同意换货";
        this.retialMsg3 = "等待买家收货";
        this.retialMsg4 = "已完成售后服务";
        this.activity=4;
      }
      this.tit1=row.sqDate;
      this.retialMsg1 = this.gkName + "发起了退货申请，" + "货物状态：已收到货物，" + "原因：" +  this.asReason + "，退款：" + this.allMoney + "￥，" +  "说明：" + this. asExplain + "。"
    },
    //同意退款退货请求
    agree(){
      if(this.asType=="换货"){
        this.asStatus = "重新发货中";
      }else if(this.asType=="退货退款"){
        this.asStatus = "退货中";
      }else if(this.asType=="仅退款"){
        this.asStatus = "拦截物流中";
      }
      this.$axios
          .get("http://localhost:8080/afterSale/agree/" + this.ddId + "/"+ this.asStatus)
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message({
                message: "成功",
                type: "success",
              });
              this.detaildata=[];
              this.dialogVisible=false;
              this.getAllAfterSale();
          }
        })
    },
    //驳回退款退货
    reject(){
      this.$axios
          .get("http://localhost:8080/afterSale/reject/" + this.ddId )
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message({
                message: "成功",
                type: "success",
              });
              this.detaildata=[];
              this.dialogVisible=false;
              this.getAllAfterSale();
          }
        })
    },
    //重新发货/退款/拦截物流
    dealRequest1(){
      if(this.asType=="换货"){
        this.asStatus = "买家已收货";
      }else if(this.asType=="退货退款"){
        this.asStatus = "收到退货";
      }else if(this.asType=="仅退款"){
        this.asStatus = "已拦截物流";
      }
      this.$axios
          .get("http://localhost:8080/afterSale/dealRequest1/" + this.ddId +"/" +this.asStatus)
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message({
                message: "处理成功",
                type: "success",
              });
              this.detaildata=[];
              this.dialogVisible=false;
              this.getAllAfterSale();
          }
        })
    },
    //买家已收货/收到退货/已拦截物流
    dealRequest2(){
        this.asStatus = "已完成";
      this.$axios
          .get("http://localhost:8080/afterSale/dealRequest2/" + this.ddId +"/" +this.asStatus)
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message({
                message: "处理成功",
                type: "success",
              });
              this.detaildata=[];
              this.dialogVisible=false;
              this.getAllAfterSale();
          }
        })
    },
    //完成售后
    finAfterSale(){
      this.detaildata=[];
      this.dialogVisible=false;
      this.getAllAfterSale();
    },
    //获取图片路径
    getImage(index,row){
      this.filed=true; this.path = "http://localhost:8080/images/" + row.spImage;
    },
    //售后标签相关方法
    filterAsType(value, row) {
      return row.asType === value;
    },
    //状态标签相关方法
    filterStatus(value, row) {
      return row.asStatus === value;
    },
  },
  };
  </script>
  
  <style>
  /* 绿色按钮 */
  .css-button-rounded--green {
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid white;
    background: #57cc99;
    margin: 3px;
  }
  .css-button-rounded--green:hover {
    background: #6ef2b9;
  }
  /* 红色按钮 */
  .css-button-rounded--red {
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid white;
    background: #f34646;
    margin: 3px;
  }
  .css-button-rounded--red:hover {
    background: #ff7e91;
  }
  /* 黄色按钮 */
  .css-button-rounded--yellow {
    
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid white;
    background: #f2ca00;
    margin: 3px;
  }
  .css-button-rounded--yellow:hover {
    background: #f7dc59;
  }
  /* 蓝色按钮 */
  .css-button-rounded--blue {
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid white;
    background: #07c1e2;
    margin: 3px;
  }
  .css-button-rounded--blue:hover {
    background: #17dee9;
  }
  /* 详情按钮 */
  .css-button-fully-rounded--yellow {
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid #f7dc59;
    background: #f7dc59;
  }
  .css-button-fully-rounded--yellow:hover {
    background: #fff;
    color: #ffd819
  }
  /* 导出按钮 */
  .css-button-rounded--export {
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid white;
    background: #3a86ff;
    margin: 3px;
  }
  .css-button-rounded--export:hover {
    background: #79b3ff;
  }
  /* 导入按钮 */
  .css-button-rounded--import {
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid white;
    background: #3d348b;
    margin: 3px;
  }
  .css-button-rounded--import:hover {
    background: #1b569e;
  }
  /* 物流按钮 */
  .css-button-arrow--sky {
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid white;
    background-color: #3a86ff
  }
  .css-button-arrow--sky:hover {
    border-radius: 5px;
    padding-right: 24px;
    padding-left:8px;
  }
  .css-button-arrow--sky:hover:after {
    opacity: 1;
    right: 10px;
  }
  .css-button-arrow--sky:after {
    content: "\00BB";
    position: absolute;
    opacity: 0;
    font-size: 20px;
    line-height: 40px;
    top: 0;
    right: -20px;
    transition: 0.4s;
  }
  /* 确认按钮 */
  .css-button-arrow--green {
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid white;
    background-color: #57cc99
  }
  .css-button-arrow--green:hover {
    border-radius: 5px;
    padding-right: 24px;
    padding-left:8px;
  }
  .css-button-arrow--green:hover:after {
    opacity: 1;
    right: 10px;
  }
  .css-button-arrow--green:after {
    content: "\00BB";
    position: absolute;
    opacity: 0;
    font-size: 20px;
    line-height: 40px;
    top: 0;
    right: -20px;
    transition: 0.4s;
  }
    /* 确认按钮 */
  .css-button-arrow--green {
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid white;
    background-color: #57cc99
  }
  .css-button-arrow--green:hover {
    border-radius: 5px;
    padding-right: 24px;
    padding-left:8px;
  }
  .css-button-arrow--green:hover:after {
    opacity: 1;
    right: 10px;
  }
  .css-button-arrow--green:after {
    content: "\00BB";
    position: absolute;
    opacity: 0;
    font-size: 20px;
    line-height: 40px;
    top: 0;
    right: -20px;
    transition: 0.4s;
  }
  /* 物流按钮 */
  .css-button-arrow--sky1 {
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid white;
    background-color: #07c1e2
  }
  .css-button-arrow--sky1:hover {
    border-radius: 5px;
    padding-right: 24px;
    padding-left:8px;
  }
  .css-button-arrow--sky1:hover:after {
    opacity: 1;
    right: 10px;
  }
  .css-button-arrow--sky1:after {
    content: "\00BB";
    position: absolute;
    opacity: 0;
    font-size: 20px;
    line-height: 40px;
    top: 0;
    right: -20px;
    transition: 0.4s;
  }
  /* 删除按钮 */
  .css-button-arrow--red {
    min-width: 80px;
    height: 40px;
    color: #fff;
    padding: 5px 10px;
    font-weight: bold;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    outline: none;
    border-radius: 5px;
    border: 2px solid white;
    background-color: #f34646
  }
  .css-button-arrow--red:hover {
    border-radius: 5px;
    padding-right: 24px;
    padding-left:8px;
  }
  .css-button-arrow--red:hover:after {
    opacity: 1;
    right: 10px;
  }
  .css-button-arrow--red:after {
    content: "\00BB";
    position: absolute;
    opacity: 0;
    font-size: 20px;
    line-height: 40px;
    top: 0;
    right: -20px;
    transition: 0.4s;
  }
  </style>