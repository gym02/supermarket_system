<template>
  <div>
    <!------------------------------ 门店信息搜索框 --------------------------------------------->
    <el-card shadow="always" style="margin-bottom: 10px;">
      <div style="margin-bottom: 5px">
        <el-form ref="form" size="small" label-width="80px" style="margin-top: 23px">
          <el-row :gutter="20">
            <!-- <el-col :span="6">
              <el-form-item label="员工号">
                <el-input v-model="shop.id"></el-input>
              </el-form-item>
            </el-col>-->
            <el-col :span="6">
              <el-form-item label="门店名">
                <el-input v-model="shopDTO.mdName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="地址">
                <el-input v-model="shopDTO.mdAddress"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="联系电话">
                <el-input v-model="shopDTO.mdPhone"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="店长">
                <el-input v-model="shopDTO.mdCheif"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="2" style="margin-left: 15px">
              <div>
                <el-button
                  type="primary"
                  size="small"
                  @click="search()"
                  class="css-button-rounded--blue"
                >
                  <i class="el-icon-search"></i>&nbsp;搜索
                </el-button>
              </div>
            </el-col>
            <el-col :span="1">
              <div>
                <button
                  type="success"
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
    <!-------------------------------------- 门店数据显示表 ------------------------------------>
    <el-card shadow="always">
      <div>
        <div style="text-align: left; margin-bottom: 15px">
          <button
            type="primary"
            size="small"
            @click="dialogVisible = true"
            class="css-button-rounded--blue"
          >
            <i class="el-icon-plus"></i>&nbsp;添加
          </button>
          <button type="danger" size="small" @click="removeAll()" class="css-button-rounded--red">
            <i class="el-icon-delete"></i>&nbsp;删除
          </button>
          <button type="success" size="small"  @click="exportExcel" class="css-button-rounded--export">
            <i class="el-icon-upload2"></i>&nbsp;导出
          </button>
          <span style="text-align: right; float: right; margin: 15px">
            <el-button size="small" plain type="info">
              <i class="el-icon-s-operation"></i>
            </el-button>
          </span>
        </div>

        <el-table
          border
          ref="multipleTable"
          :data="shops"
          tooltip-effect="dark"
          style="width: 100%"
          :default-sort="{ shop: 'descending' }"
          :header-cell-style="{
            background: '#F5F7FA',
            'text-align': 'center',
            color: '#606266',
          }"
          stripe
          highlight-current-row
          @selection-change="handleSelectionChange"
          @row-click="handleRowClick"
        >
          <el-table-column type="selection" width="55" class="myRedCheckBox"></el-table-column>
          <el-table-column
            align="center"
            label="门店编号"
            width="160"
            show-overflow-tooltip
            sortable
            prop="mdId"
            fixed
          ></el-table-column>
          <el-table-column
            align="center"
            label="门店名"
            width="160"
            show-overflow-tooltip
            prop="mdName"
          ></el-table-column>
          <el-table-column
            align="center"
            label="地址"
            width="180"
            show-overflow-tooltip
            prop="mdAddress"
          ></el-table-column>
          <el-table-column
            align="center"
            prop="mdPhone"
            label="电话"
            width="160"
            show-overflow-tooltip
            sortable
          ></el-table-column>
          <el-table-column
            align="center"
            prop="mdCheif"
            label="店长"
            width="160"
            show-overflow-tooltip
            sortable
          ></el-table-column>
          <el-table-column label="图片" width="280" show-overflow-tooltip sortable>
            <template slot-scope="scope">
              <img
                :src="'http://localhost:8080/images/'+scope.row.mdLicenseSrc"
                width="260px"
                height="200px"
              />
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            prop="mdDescribe"
            label="备注"
            width="160"
            show-overflow-tooltip
            sortable
          ></el-table-column>
          <el-table-column label="操作" fixed="right" align="center">
            <template slot-scope="scope">
              <button
                size="small"
                plain
                type="warning"
                @click="showUpdate(scope.$index, scope.row)"
                class="css-button-rounded--yellow"
              >
                <i class="el-icon-edit"></i>&nbsp;编辑
              </button>

              <button
                size="small"
                type="danger"
                @click="remove(scope.$index, scope.row)"
                class="css-button-rounded--red"
              >
                <i class="el-icon-delete"></i>&nbsp;删除
              </button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
    <!-- 对话框 -->
    <el-dialog title="show" :visible.sync="dialogVisible">
      <el-form ref="shop" :model="shop" :rules="rules" label-width="60px">
        <el-form-item label="门店名" :label-width="formLabelWidth" prop="mdName">
          <el-input v-model="shop.mdName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" :label-width="formLabelWidth" prop="mdAddress">
          <el-input v-model="shop.mdAddress" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth" prop="mdPhone">
          <el-input v-model="shop.mdPhone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="店长" :label-width="formLabelWidth" prop="mdCheif">
          <el-input v-model="shop.mdCheif" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图片" :label-width="formLabelWidth" prop="mdLicenseSrc">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:8080/shop/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img
              v-if="shop.mdLicenseSrc"
              :src="'http://localhost:8080/images/'+shop.mdLicenseSrc"
              class="avatar"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth" prop="mdDescribe">
          <el-input v-model="shop.mdDescribe" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle()">取 消</el-button>
        <el-button type="primary" @click="addOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
  
  <script>
export default {
  data() {
    return {
      rules: {},
      formLabelWidth: "120",
      // 对话框添加或删除标记
      addFlag: true,
      // 存储修改行的下标
      updateIndex: "",
      //表格显示数据，与后端进行绑定
      shops: [],
      // 显示添加对话框
      dialogVisible: false,
      // 添加员工绑定属性
      shop: {mdLicenseSrc:'',
          mdDescribe:''
      },
      // 搜索员工绑定属性
      shopDTO: {
        mdName: "",
        mdAddress: "",
        mdPhone: "",
        mdCheif: "",
        mdLicenseSrc: ""
      },
      // 多选框选中的数据，删除时传输后台这个
      multipleSelection: [],
      // 临时变量，记录多选框删除数据
      selectedRows: [],
      // 选中当前行高亮
      currentRow: null
    };
  },
  mounted() {
    this.search();
  },
  methods: {
    exportExcel(){
         /**
         * 下载文件的功能不能使用axios发请求，因为axios的响应会被封装到res中，在前端没办法处理。
         * 只能使用原生的浏览器直发请求
         */
        let url = "http://localhost:8080/shop/export?";
        if(this.shop.mdId){
          url += "mdId="+this.shop.mdId;
        }

        if(this.shop.mdName){
          url += "&mdName="+this.shop.mdName;
        }
         if(this.shop. mdAddress){
          url += "&mdAddress="+this.shop.mdAddress;
        }
        if(this.shop. mdPhone){
          url += "&mdPhone="+this.shop.mdPhone;
        }
        if(this.shop.mdCheif){
          url += "&mdCheif="+this.shop.mdCheif;
        }
         if(this.shop.mdDescribe){
          url += "&mdDescribe="+this.shop.mdDescribe;
        }

        window.location.href = url;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      // if (!isLt2M) {
      //     this.$message.error('上传头像图片大小不能超过 20MB!');
      // }
      return isJPG;
    },
    handleAvatarSuccess(res, file) {
      console.log(res);
      this.shop.mdLicenseSrc = res.data;
      console.log(this.shop.mdLicenseSrc);
    },
    // 添加人员
    addOrUpdate() {
      if (this.addFlag == true) {
        this.$axios
          .post("http://localhost:8080/shop/add", this.shop)
          .then(r => {
            if (r.data.code === 200) {
              this.$message.success("添加成功");
              this.search();
              this.shop={};
            } else if (r.data.code === 100) {
              this.$message.error("验证失败" + JSON.stringify(r.data.data));
            } else if (r.data.code === 500) {
              this.$message.error("验证失败" + JSON.stringify(r.data.data));
            }
          });
        this.shop = {};
        this.dialogVisible = false;
      } else {
        console.log(this.shop);
        // 修改
        this.$axios
          .post("http://localhost:8080/shop/edit", this.shop)
          .then(r => {
            if (r.data.code == 200) {
              this.$message.success("修改成功");
              this.search();
            } else if (r.data.code === 100) {
              this.$message.error("验证失败" + JSON.stringify(r.data.data));
            } else if (r.data.code === 500) {
              this.$message.error("注册失败" + JSON.stringify(r.data.data));
            }
              this.dialogVisible = false;
              this.addFlag;
              this.shop = {};
          });
      }
    },
    // 取消添加人员
    cancle() {
      this.dialogVisible = false;
      // 这样清空真的很快，不用傻乎乎每个属性都赋空
      this.shop = {};
    },
    // 删除人员
    remove(index, row) {
      console.log(row.mdId)
      this.$confirm("此操作将删除信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$axios
          .post("http://localhost:8080/shop/deleteByMdId/"+row.mdId)
          .then(r=>{
            if(r.data.code===200){
              this.$message.success("删除成功")
              this.search();
            }else{
              this.$message.error("删除失败")
            }
          })
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    // 数据回显
    showUpdate(index, row) {
      this.addFlag = false;
      this.shop = {...row};
      this.updateIndex = index;
      this.dialogVisible = true;
      //   this.$axios.post("http://localhost:8080/shop/getByMdId/"+row.mdId)
      // .then(r=>{
      //   this.shop=r.data.data;
      // })
    },
    //点击一行也加入多选框
    handleRowClick(row, column, event) {
      // 点击某一行时，切换该行的勾选状态，并更新selectedRows数组
      this.$refs.multipleTable.toggleRowSelection(row);
      let index = this.selectedRows.indexOf(row);
      if (index > -1) {
        this.selectedRows.splice(index, 1);
      } else {
        this.selectedRows.push(row);
      }
    },
    // 用于保存选中的行
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
    },
    // 删除所有人员
    removeAll() {
      if(this.multipleSelection.length==0){
        this.$message.error("请至少选中一条数据");
        return
      }
      this.$confirm("此操作将批量删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          let mdIds=[]
          for(let m of this.multipleSelection){
            mdIds.push(m.mdId)
          }
          this.$axios.post("http://localhost:8080/shop/removeAll",{
            mdIds
          }).then(r=>{
            if(r.data.code===200){
              this.$message.success("删除成功")
              this.search();
            }else{
              this.$message.error("删除失败")
            }
          })
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    // 搜索人员
    search() {
      this.$axios
        .post("http://localhost:8080/shop/getAll", this.shopDTO)
        .then(r => {
          if (r.data.code === 200) {
            this.shops = r.data.data;
            console.log(r.data.data);
          }
        });
    },
    // 搜索重置
    reset() {
      this.shop = {};
      this.$message({
        type: "success",
        message: "重置成功"
      });
    }
  }
};
</script>
  
  <style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
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
/* 黄色按钮 */
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
</style>