<template>
    <div class="xp-footerbar">
        <footer class="footer">
            <p class="mb-0">&copy;&nbsp;<slot></slot></p>
        </footer>
    </div>
</template>

<script>
export default {
    name: "BottomBar"
}
</script>

<style scoped lang="scss">
$m-size: 768px;
$m-size-1: $m-size - 1px; //767 px

$color-border-top: #e1e4e9;

.xp-footerbar {
    position: absolute;
    bottom: 0;
    right: 0;
    left: 250px;
    padding: 20px 30px;
    text-align: center;
    border-top: 1px solid $color-border-top;

    @media print {
        display: none;
    }

    @media (max-width: $m-size-1) {
        left: 0;
    }
}

</style>
