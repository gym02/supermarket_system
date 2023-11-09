<template>
    <div>
      <!------------------------------ 员工数据搜索框 --------------------------------------------->
      <el-card shadow="always" style="margin-bottom: 10px;">
      <div style="margin-bottom: 5px">
        <el-form
          ref="form"
          size="small"
          label-width="80px"
          style="margin-top: 23px"
        >
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="员工号">
                <el-input v-model="searchPerson.id"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="姓名">
                <el-input v-model="searchPerson.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="性别">
                <el-radio label="男" v-model="searchPerson.gender">男</el-radio>
                <el-radio label="女" v-model="searchPerson.gender">女</el-radio>
              </el-form-item>
            </el-col>
          </el-row>
  
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="部门">
                <el-select v-model="searchPerson.department" placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="地址">
                <el-input v-model="searchPerson.address"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="出生日期">
                <el-date-picker
                  type="date"
                  v-model="searchPerson.birthday"
                  placeholder="选择日期"
                  value-format="yyyy-MM-dd"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="2" style="margin-left: 15px">
              <div>
                <button type="primary" size="small" @click="search()" class="css-button-rounded--blue"
                  ><i class="el-icon-search"></i>&nbsp;搜索</button
                >
              </div>
            </el-col>
            <el-col :span="1">
              <div>
                <button type="success" size="small" @click="reset()" class="css-button-rounded--green"
                  ><i class="el-icon-refresh"></i>&nbsp;重置</button
                >
              </div>
            </el-col>
          </el-row>
        </el-form>
      </div>
      </el-card>
      <!---- 员工数据显示表 ----->
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
          <button type="success" size="small" class="css-button-rounded--export">
            <i class="el-icon-upload2"></i>&nbsp;导出
          </button>
          <button type="warning" size="small" class="css-button-rounded--import">
            <i class="el-icon-download"></i>&nbsp;导入
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
          :data="allPerson"
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
          @row-click="handleRowClick"
        >
          <el-table-column type="selection" width="55" class="myRedCheckBox"> </el-table-column>
          <el-table-column
            align="center"
            label="员工号"
            width="160"
            show-overflow-tooltip
            sortable
            prop="id"
            fixed
          >
            <template slot-scope="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="姓名"
            width="160"
            show-overflow-tooltip
            prop="name"
          >
            <template slot-scope="scope">{{ scope.row.name }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            prop="gender"
            label="性别"
            width="160"
            show-overflow-tooltip
            sortable
          >
            <template slot-scope="scope">{{ scope.row.gender }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            prop="department"
            label="部门"
            width="100"
            :filters="[
              { text: 'IT', value: 'IT' },
              { text: '财务', value: '财务' },
            ]"
            :filter-method="filterTag"
            filter-placement="bottom-end"
          >
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.department === 'IT' ? 'primary' : 'success'"
                disable-transitions
                >{{ scope.row.department }}</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            prop="address"
            label="家庭地址"
            width="160"
            show-overflow-tooltip
            sortable
          >
            <template slot-scope="scope">{{ scope.row.address }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            prop="date"
            label="出生日期"
            width="160"
            show-overflow-tooltip
            sortable
          >
            <template slot-scope="scope">{{ scope.row.birthday }}</template>
          </el-table-column>
  
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
        <el-form ref="form" :model="person" label-width="60px">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="员工号">
                <el-input v-model="person.id"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="姓名">
                <el-input v-model="person.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
  
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="性别">
                <el-radio label="男" v-model="person.gender">男</el-radio>
                <el-radio label="女" v-model="person.gender">女</el-radio>
              </el-form-item>
            </el-col>
            <el-col :span="4"> &nbsp; </el-col>
            <el-col :span="12">
              <el-form-item label="部门">
                <el-select v-model="person.department" placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
  
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="地址">
                <el-input v-model="person.address"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="生日">
                <el-date-picker
                  type="date"
                  placeholder="选择日期"
                  v-model="person.birthday"
                  value-format="yyyy-MM-dd"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
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
        // 对话框添加或删除标记
        addFlag: true,
        // 存储修改行的下标
        updateIndex: "",
        //表格显示数据，与后端进行绑定
        allPerson: [
          {
            id: "1001",
            name: "张三",
            gender: "男",
            department: "IT",
            address: "重庆市",
            birthday: "2000-06-01",
          },
          {
            id: "1003",
            name: "李四",
            gender: "男",
            department: "财务",
            address: "四川省成都市",
            birthday: "2003-04-01",
          },
          {
            id: "1002",
            name: "王五",
            gender: "男",
            department: "IT",
            address: "山东省济南市",
            birthday: "1998-03-01",
          },
          {
            id: "1004",
            name: "杨六",
            gender: "女",
            department: "财务",
            address: "台湾省",
            birthday: "2002-09-01",
          },
        ],
        // 显示添加对话框
        dialogVisible: false,
        // 添加员工绑定属性
        person: {
          id: "",
          name: "",
          gender: "",
          department: "",
          address: "",
          birthday: "",
        },
        // 搜索员工绑定属性
        searchPerson: {
          id: "",
          name: "",
          gender: "",
          department: "",
          address: "",
          birthday: "",
        },
        // 部门
        options: [
          {
            value: "IT",
            label: "IT",
          },
          {
            value: "财务",
            label: "财务",
          },
        ],
        // 多选框选中的数据，删除时传输后台这个
        multipleSelection: [],
        // 临时变量，记录多选框删除数据
        selectedRows:[],
        // 选中当前行高亮
        currentRow: null,
      };
    },
  
    methods: {
      // 添加人员
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
      // 取消添加人员
      cancle() {
        this.dialogVisible = false;
        // 这样清空真的很快，不用傻乎乎每个属性都赋空
        this.person = {};
      },
      // 删除人员
      remove(index, row) {
        this.$confirm("此操作将删除信息, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
            .then(() => {
              this.allPerson.splice(index, 1);
              this.$message({
                type: "success",
                message: "删除成功!"
              });
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
        this.person = row;
        this.updateIndex = index;
        this.dialogVisible = true;
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
        },
      // 删除所有人员
      removeAll() {
        this.$confirm('此操作将批量删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(this.multipleSelection)
          let val = this.multipleSelection     //选中的值
          if (val) {
            val.forEach((val, index) => {                 // 这块看不明白的话看下面的解释
              this.allPerson.forEach((v, i) => {   
                if (val === v) {
                  this.allPerson.splice(i, 1)
                }
              })
            })
          }
          this.$message.success("删除成功")
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
        this.$message({
          type: "success",
          message: "自己拿到后端查~",
        });
      },
      // 搜索重置
      reset() {
        this.searchPerson = {};
        this.$message({
          type: "success",
          message: "重置成功",
        });
      },
  
  
      // 部门标签相关方法
      formatter(row, column) {
        return row.department;
      },
      // 部门标签相关方法
      filterTag(value, row) {
        return row.department === value;
      },
      // 部门标签相关方法
      filterHandler(value, row, column) {
        const property = column["property"];
        return row[property] === value;
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
    background:   #3a86ff;
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
    background:     #3d348b;
    margin: 3px;
  }
  .css-button-rounded--import:hover {
    background: #1b569e;
  }
  </style>