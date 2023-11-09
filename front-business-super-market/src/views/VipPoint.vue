<template>
  <!-- 积分兑换 -->
  <div>
    <el-card shadow="always" style="margin-bottom: 10px">
      <span style="font-weight:bold">积分兑换</span>
      <el-alert
        style="text-align: left; margin-top: 10px"
        :closable="false"
        title="积分兑换"
        description="选择积分商品，填写兑换人信息，进行兑换"
        show-icon
      >
      </el-alert>

      <!-- 商品展示 -->
      <el-table
        border
        ref="multipleTable"
        :data="vipIndents"
        tooltip-effect="dark"
        style="width: 100%;margin-top:20px"
        :default-sort="{ order: 'descending' }"
        :header-cell-style="{
          background: '#F5F7FA',
          'text-align': 'center',
          color: '#606266',
        }"
        stripe
        highlight-current-row
        height="420"
        width="100%"
      >

        <el-table-column
          align="center"
          label="商品id"
          width="250"
          show-overflow-tooltip
          prop="viId"
          sortable
        >
          <template slot-scope="scope">{{ scope.row.viId }}</template>
        </el-table-column>

        <el-table-column
          align="center"
          label="商品名字"
          width="250"
          show-overflow-tooltip
          prop="viName"
        >
          <template slot-scope="scope">{{ scope.row.viName }}</template>
        </el-table-column>

        <el-table-column
          align="center"
          prop="viPoint"
          label="所需积分"
          width="250"
          sortable
          show-overflow-tooltip
        >
          <template slot-scope="scope">{{ scope.row.viPoint }}</template>
        </el-table-column>

        <el-table-column
          align="center"
          prop="viNumber"
          label="库存数量"
          width="250"
          sortable
          show-overflow-tooltip
        >
          <template slot-scope="scope">{{ scope.row.viNumber }}</template>
        </el-table-column>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <button
              size="small"
              plain
              type="warning"
              @click="exchange(scope.$index, scope.row)"
              class="css-button-arrow--green"
            >
              <i class="el-icon-present"></i>&nbsp;兑换
            </button>
          </template>
        </el-table-column>
      </el-table>

    <!-- 添加商品兑换对话框 -->
    <el-dialog title="添加商品兑换信息" :visible.sync="dialogVisible" width="450px">
      <el-form
        :model="vipExchange"
        :ref="vipExchange"
        label-width="100px"
        class="demo-ruleForm"
        style="text-align: center"
        status-icon
      >

      <el-row>
          <el-col :span="20">
            <el-form-item label="商品编号" prop="viId">
              <el-input v-model="vipExchange.viId" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="会员卡号" prop="hyId">
              <el-input v-model="vipExchange.hyId" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle()">取 消</el-button>
        <el-button type="primary" @click="add()">确 定</el-button>
      </div>
    </el-dialog>
    </el-card>

    
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 兑换对话框
      dialogVisible:false,
      // 所有信息
      vipIndents: [],
      // 绑定兑换对象
      vipExchange:{
        hyId:"",
        viId:"",
      },
      // 兑换商品下标
      addIndex:"",
    };
  },
  created() {
    this.allVipIndents();
  },
  methods: {
    // 查询所有信息
    allVipIndents() {
      this.$axios.get("http://127.0.0.1:8080/vipIndent/all").then((resp) => {
        this.vipIndents = resp.data.data;
      });
    },
    // 兑换商品
    exchange(index,row) {
      this.dialogVisible = true;
      this.vipExchange.viId = row.viId;
      this.addIndex = index;
    },
    // 取消
    cancle(){
      this.dialogVisible = false;
    },
    // 兑换
    add(){
      this.$axios.post("http://127.0.0.1:8080/vipExchange/add",this.vipExchange).then((resp) => {
        if(resp.data.code == 200){
          this.$message.success("兑换成功");
          this.vipIndents[this.addIndex].viNumber = this.vipIndents[this.addIndex].viNumber - 1;
          this.dialogVisible = false;
        }else if(resp.data.code == 400){
          this.$message.error("会员不存在")
        }else if(resp.data.code == 401){
          this.$message.error("会员积分不足")
        }
      });
    }
  },
};
</script>

<style>
.css-button-arrow--green {
  min-width: 130px;
  height: 40px;
  color: #fff;
  padding: 5px 10px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  outline: none;
  overflow: hidden;
  border-radius: 5px;
  border: none;
  background-color:  #57cc99;
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
</style>