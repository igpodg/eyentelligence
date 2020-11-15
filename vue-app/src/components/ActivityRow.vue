<template>
    <div :class="'body row' + (last ? ' last' : '')">
        <div class="col-sm-12 col-md-3 left">
            <span :class="'rank' + (small ? ' small' : '')">{{ rank }}.</span>
            <img :src="image" alt="user-profile"
                 :class="'rounded-circle img-fluid' + (small ? ' small' : '')">
        </div>
        <div class="col-9 right">
            <div>
                <h4 v-if="!small">{{ name }}</h4>
                <h5 v-else>{{ name }}</h5>
                <small class="text-muted tip">{{ tip }}</small>
            </div>
            <div class="time">
                <span>{{ time }}</span>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "ActivityRow",
    props: {
        image: {
            type: String,
            default: "assets/img/user.svg"
        },
        name: String,
        rank: Number,
        tip: String,
        time: String,
        small: {
            type: Boolean,
            default: false
        },
        last: {
            type: Boolean,
            default: false
        }
    }
}
</script>

<style scoped lang="scss">
$color-text-main: #2b343a;
$color-text-secondary: $color-text-main;
$color-time: #8a98ac;

$size-text-big: 60px;
$size-text-small: 45px;

$size-font-rank-big: 24px;
$weight-font-rank-big: 700;

$size-font-rank-small: 20px;
$weight-font-rank-small: 700;

$size-font-time: 17px;
$weight-font-time: 500;

.body.row {
    //background-color: gainsboro;
    margin-bottom: 20px;

    &.last {
        margin-bottom: 0;
    }

    .left {
        //background-color: gainsboro;
        text-align: center;
        padding-right: 0;

        @mixin rank-text($size, $weight, $margin) {
            font-size: $size;
            font-weight: $weight;
            margin-right: $margin;
        }

        .rank {
            //color: $color-text-main;

            @include rank-text($size-font-rank-big,
                $weight-font-rank-big, 15px);

            &.small {
                @include rank-text($size-font-rank-small,
                    $weight-font-rank-small, 30px);
            }
        }
    }

    .right {
        padding-left: 0;

        display: flex;
        flex-wrap: wrap;
        align-items: center;

        @mixin right-text($color) {
            margin-bottom: 0;
            color: $color !important;

            //flex: 0 0 100%;
        }

        //>div {
        //    flex: 0 0 100%;
        //}

        .time {
            margin-left: auto;
            padding-right: 5px;

            >span {
                font-size: $size-font-time;
                font-weight: $weight-font-time;
                color: $color-time !important;
            }
        }

        h4 {
            @include right-text($color-text-main);
        }

        h5 {
            @include right-text($color-text-secondary);
        }
    }

    img {
        width: $size-text-big;

        &.small {
            width: $size-text-small;
        }
    }
}

</style>
