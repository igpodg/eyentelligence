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
.xp-footerbar {
    position: absolute;
    bottom: 0;
    right: 0;
    left: 250px;
    padding: 20px 30px;
    text-align: center;
    border-top: 1px solid #e1e4e9;
}

@media print {
    .xp-footerbar {
        display: none;
    }
}

@media (min-width: 768px) and (max-width: 991px) {
    body {
        overflow-x: hidden;
    }
}

@media (max-width: 767px) {
    body {
        overflow-x: hidden;
    }

    .xp-footerbar {
        left: 0;
    }
}

</style>
