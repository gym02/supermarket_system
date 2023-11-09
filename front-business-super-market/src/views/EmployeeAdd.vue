<template>
  <div>
    <div>
      <el-form
        ref="employeeVO"
        :model="employeeVO"
        :rules="rules"
        label-width="80px"
        style="text-align: left"
      >
        <el-form-item label="姓名" prop="empName">
          <el-input
            v-model="employeeVO.empName"
            placeholder="请输入姓名"
          ></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input
            v-model="employeeVO.empAge"
            placeholder="请输入年龄"
          ></el-input>
        </el-form-item>

        <el-form-item label="性别">
          <el-radio v-model="employeeVO.empGender" label="男">男</el-radio>
          <el-radio v-model="employeeVO.empGender" label="女">女</el-radio>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input
            v-model="employeeVO.empPhone"
            placeholder="请输入手机号"
          ></el-input>
        </el-form-item>
        <el-form-item label="添加时间">
          <el-date-picker
            v-model="employeeVO.empDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="门店">
          <el-select v-model="employeeVO.shop.mdId" placeholder="请选择">
            <el-option
              v-for="s in shops"
              :key="s.mdId"
              :label="s.mdName"
              :value="s.mdId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="员工照片">
          <el-upload
            :data="uploadArgs"
            ref="upload"
            :on-change="selectFile"
            :auto-upload="false"
            :limit="1"
            :multiple="false"
            action="http://localhost:8080/file/upload"
            :on-success="uploaded"
            list-type="picture-card"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
      
    

        <el-form-item>
          <el-button type="primary" @click="addEmployee('employeeVO')"
            >添加</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import SparkMD5 from 'spark-md5'

export default {
  data() {
    // }

    return {
     
      
      uploadArgs: {
        md5: "",
      },
      employeeVO: { file: { id: null }, shop: { mdId: null }, user: {} },
      shops: [
        { mdId: 1, mdName: "万达" },
        { mdId: 2, mdName: "家乐福" },
      ],

      rules: {
        empName: [
          { required: true, message: "请输入名字", trigger: "blur" },
          { min: 2, max: 5, message: "长度在 2 到 5 个字符", trigger: "blur" },
        ]
        /* email: [
           { validator: checkEmail, trigger: 'blur' }
          ]*/
      },
    };
  },
  created() {
    //this.getAllShops();
  },
  methods: {
     handleChange(value) {
        console.log(value);
      },
    selectFile(f) {
      let that = this;
      let file = f.raw;
      //计算文件md5
      let blobSlice =
          File.prototype.slice ||
          File.prototype.mozSlice ||
          File.prototype.webkitSlice,
        chunkSize = 2097152, // Read in chunks of 2MB
        chunks = Math.ceil(file.size / chunkSize),
        currentChunk = 0,
        spark = new SparkMD5.ArrayBuffer(),
        fileReader = new FileReader();
      fileReader.onload = function (e) {
        console.log("read chunk nr", currentChunk + 1, "of", chunks);
        spark.append(e.target.result); // Append array buffer
        currentChunk++;
        if (currentChunk < chunks) {
          loadNext();
        } else {
          console.log("finished loading");
          let md5 = spark.end();
          console.log("md5="+md5)
          //向后端发请求，查询有没有这个文件
          that.$axios.get("http://localhost:8080/file/getByMd5?md5=" + md5).then((res) => {
            //文件不存在
            if (res.data.code == 400) {
              //把计算出来的md5值存入uploadArgs中
              that.uploadArgs.md5 = md5;
              //上传文件，获取上传文件的id
              //1.使用axios
              //2.手动调upload中的submit函数
              that.$refs.upload.submit();
              //有这个文件
            } else {
              //直接把id存起来下次提交上去
              that.employeeVO.file = {
                id: res.data.data.id,
              };
            }
          });
        }
      };
      fileReader.onerror = function () {
        console.warn('oops, something went wrong.');
      };
      function loadNext() {
        var start = currentChunk * chunkSize,
                end = ((start + chunkSize) >= file.size) ? file.size : start + chunkSize;
        fileReader.readAsArrayBuffer(blobSlice.call(file, start, end));
      }
      loadNext();
    },

    uploaded(res) {
      //文件上传成功后，把后端响应回来的文件标识存入building中，下次提交数据时就会自动提交到后端
      this.employeeVO.file = {
        id: res.data.data,
      };
    },

    getAllShops() {
      let shop = {};
      this.$axios
        .post("http://localhost:8080/shop/get/all", shop)
        .then((resp) => {
          this.shops = resp.data.data;
        });
    },
    addEmployee(employeeVO) {
      this.$refs[employeeVO].validate((valid) => {
        if (valid) {
          this.$axios
            .post("http://localhost:8080/employee/add", this.employeeVO)
            .then((resp) => {
              if (resp.data.code == 200) {
                this.$message("添加成功");
                this.employeeVO = {
                  file: { id: null },
                  shop: { mdId: null },
                  user: {},
                };
              } else if (resp.data.code == 100) {
                this.$message("验证失败" + JSON.stringify(resp.data.data));
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style>
</style>