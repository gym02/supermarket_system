<template>
  <div>
    <!-- 消费金额和积分映射关系 -->
    <el-card shadow="always" style="margin-bottom: 10px">
      <span style="font-weight:bold">积分映射</span>

      <!-- 积分修改提示 -->
      <el-alert
        style="text-align: left; margin-top: 10px"
        :closable="true"
        title="积分映射关系"
        description="消费金额转换成相应积分的映射关系"
        show-icon
      >
      </el-alert>

      <el-form style="margin-top: 10px">
        <el-row :gutter="50">
          <el-col :span="5">
            <el-form-item label="消费金额" style="text-align: center">
              <el-input v-model="mappingAmountPoint.amount"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="1" style="margin-top: 40px">
            <el-form-item style="text-align: center; size: 20px">
              /
            </el-form-item>
          </el-col>

          <el-col :span="5">
            <el-form-item label="积分" style="text-align: center">
              <el-input v-model="mappingAmountPoint.point"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="3" style="margin-top: 40px; margin-left: -50px">
            <el-button plain type="info" @click="calculateRatio">=</el-button>
          </el-col>

          <el-col :span="5" style="margin-top: 0px; margin-left: -50px">
            <el-form-item label="换算比" style="text-align: center">
              <el-input v-model="ratio" disabled></el-input>
            </el-form-item>
          </el-col>

          <button
            style="margin-top: 40px; margin-left: -250px"
            type="button"
            @click="updateAmountPointRatio()"
            class="css-button-rounded--green"
          >
            确认修改
          </button>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 计算属性
      mappingAmountPoint: { amount: "", point: "" },
      //   计算结果
      ratio: "",
    };
  },
  methods: {
    // 计算消费金额和积分之间的转换率
    calculateRatio() {
      if (this.mappingAmountPoint.amount && this.mappingAmountPoint.point) {
        this.ratio = (
          this.mappingAmountPoint.amount / this.mappingAmountPoint.point
        ).toFixed(2);
      } else {
        this.ratio = "";
      }
    },
    // 修改数据库中所有的积分
    updateAmountPointRatio() {
      this.$confirm("此操作将修改现有会员积分, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          if(this.ratio == ''){
            this.$message.error("输入数据非法");
          }else{
            this.$axios
            .post(
              "http://127.0.0.1:8080/vip/updateAmountPointRatio/" + this.ratio
            )
            .then((resp) => {
              if (resp.data.code == 200) {
                this.$message.success("修改成功");
              }
            });
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
};
</script>  


<style>

</style>