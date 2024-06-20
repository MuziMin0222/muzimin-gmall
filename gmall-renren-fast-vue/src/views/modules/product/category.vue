<template>
  <div>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%">
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addCategory">确 定</el-button>
      </span>
    </el-dialog>

    <el-tree :data="menu"
             :props="defaultProps"
             @node-click="handleNodeClick"
             show-checkbox
             node-key="catId"
             :expand-on-click-node="false"
             :default-expanded-keys="expandedKey">
     <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button v-if="node.level <= 2"
                     type="text"
                     size="mini"
                     @click="() => append(data)">
            Append
          </el-button>
          <el-button v-if="node.childNodes.length === 0"
                     type="text"
                     size="mini"
                     @click="() => remove(node, data)">
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>
  </div>
</template>

<script>
export default {
  data() {
    return {
      category: {name: '', parentCid: 0, catLevel: 0, showStatus: 1, sort: 0},
      dialogVisible: false,
      menu: [],
      expandedKey: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    };
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
    },
    getMenus() {
      this.$http({
        url: this.$http.adornUrl('/category/list/tree'),
        method: 'get'
      }).then(({data}) => {
        console.log('成功获取到数据。。。', data.data)
        this.menu = data.data
      })
    },
    addCategory() {
      console.log('提交的三级分类数据：', this.category)
      this.$http({
        url: this.$http.adornUrl('/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({data}) => {
        this.$message({
          message: '菜单添加成功',
          type: 'success'
        });
        this.dialogVisible = false
        this.getMenus()
        this.expandedKey = [this.category.parentCid];
      })
    },
    append(data) {
      console.log('append', data)
      this.dialogVisible = true
      this.category.parentCid = data.catId
      this.category.catLevel = data.catLevel * 1 + 1
    },

    remove(node, data) {
      var ids = [data.catId]

      this.$confirm(`是否删除【${data.name}】菜单?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/category/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          console.log(`删除数据${ids}成功`)
          this.$message({
            message: '菜单删除成功',
            type: 'success'
          });
          this.getMenus();
          this.expandedKey = [node.parent.data.catId];
        })
      }).catch(() => {
      })
    }
  },
  created() {
    this.getMenus()
  }
}
</script>

<style>

</style>
