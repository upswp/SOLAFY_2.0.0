<template>
    <q-chip v-if="active" clickable @click="$emit('click')" class="active-menu-btn" text-color="white" :label="label" :style="{'padding-left': padding}"/>
    <q-chip v-else clickable @click="$emit('click')" class="inactive-menu-btn" text-color="black" :label="label" :style="{'padding-left': padding}"/>
</template>
<script>
export default {
    name:"MenuButton",
    props:{
        active:{
            type:Boolean,
            default:false
        },
        label:{
            type:String,
            default: ""
        },
        padding:{
            type:String,
            default:"0px"
        }
    }
}
</script>
<style lang="sass" scoped>
.active-menu-btn
    width: 240px
    height: 22px
    font-size: 14px
    background: #0094FF
.inactive-menu-btn
    width: 240px
    height: 22px
    font-size: 14px
    background: white
</style>