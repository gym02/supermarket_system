<template>
  <div>
    <el-card shadow="always" style="margin-bottom: 10px">
      <span style="font-weight:bold;"> 余额充值 </span>
      <el-form
        :model="vipBalance"
        :rules="rules"
        :ref="vipBalance"
        label-width="100px"
        class="demo-ruleForm"
        status-icon
        style="margin-left:430px;margin-top:20px"
      >
        <el-row>
          <el-col :span="10">
            <el-form-item label="会员卡号" prop="hyId">
              <el-input v-model="vipBalance.hyId"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        
            <el-form-item label="充值类型" prop="czType" style="text-align:left">
              <el-select v-model="vipBalance.czType" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>

        <el-row>
          <el-col :span="10">
            <el-form-item label="充值余额" prop="hyBalance">
              <el-input v-model.number="vipBalance.hyBalance"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item style="text-align: left">
          <button
            type="button"
            @click="submitForm()"
            class="css-button-rounded--green"
          >
            充值
          </button>
          <button
            type="button"
            @click="resetForm('vipAdd')"
            class="css-button-rounded--green"
          >
            重置
          </button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    // 检验输入余额数据合法性
    var checkBalance = (rule, value, callback) => {
      if (value == 0) {
      } else {
        if (!value) {
          return callback(new Error("请输入充值金额"));
        }
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error("请输入数字值"));
        } else {
          if (value < 0) {
            callback(new Error("充值金额不能小于0"));
          } else {
            callback();
          }
        }
      }, 1000);
    };
    return {
      // 充值id和金额
      vipBalance: {
        hyId: "",
        czType: "",
        hyBalance: "",
      },
      // 充值类型
      options:[{value:"微信",label:"微信"},
      {value:"支付宝",label:"支付宝"},
      {value:"现金",label:"现金"}],
      // 数据校验规则
      rules: {
        hyId: [
          { required: true, message: "请输入会员卡号", trigger: "blur" },
          { min: 6, message: "长度不小于 6 个字符", trigger: "blur" },
        ],
        hyBalance: [
          { required: true, validator: checkBalance, trigger: "blur" },
        ],
        czType: { required: true, message: '请选择充值类型', trigger: 'change' }
      },
    };
  },
  methods: {
    submitForm() {
      try{
        this.$axios
        .post("http://127.0.0.1:8080/vip/addBalance", this.vipBalance)
        .then((resp) => {
          if (resp.data.code == 200) {
            this.$message.success("充值成功");
            this.resetForm();
          } else if (resp.data.code == 400) {
            this.$message.error("会员账号不存在");
          } else {
            this.$message.error("输入数据非法");
          }
        });
      }catch{
        this.$message.error("输入数据非法");
      }
    },
    resetForm() {
      this.vipBalance = {
        hyId: "",
        czType: "",
        hyBalance: "",
      };
    },
  },
};
</script>

<style>
</style>

