<template>
  <el-tree :data="menu"
           :props="defaultProps"
           @node-click="handleNodeClick"
           show-checkbox
           node-key="catId"
           :expand-on-click-node="false">
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
</template>

<script>
export default {
  data() {
    return {
      menu: [],
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
    append(data) {
      console.log('append', data)
    },

    remove(node, data) {
      console.log('remove', node, data)
    }
  },
  created() {
    this.getMenus()
  }
}
</script>

<style>

</style>
