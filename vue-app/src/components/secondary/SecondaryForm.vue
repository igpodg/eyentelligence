<template>
    <div id="xp-container" class="xp-container">
        <div class="row secondary-content">
            <div class="col-lg-12">
                <div class="xp-auth-box">
                    <h3 class="text-center">
                        <router-link to="/" class="xp-web-logo">
                            <img :src="this.$logoPath" alt="Logo">
                        </router-link>
                    </h3>
                    <div class="p-3">
                        <form @submit="formSubmitted">
                            <div class="text-center mb-3">
                                <h4 class="text-black">{{ title }}</h4>
                                <p class="text-muted" v-if="subtitle !== undefined">{{ subtitle }}</p>
                            </div>
                            <hr :class="tall ? 'tall-line' : ''">
                            <div class="secondary-form-body">
                                <slot></slot>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "SecondaryForm",
    props: {
        title: String,
        subtitle: String,
        tall: {
            type: Boolean,
            default: false
        }
    },
    methods: {
        formSubmitted: function() {
            return false;
        }
    }
}
</script>

<style lang="scss">
$m-size: 768px;
$m-size-1: $m-size - 1px; //767 px
$l-size: 992px;
$l-size-1: $l-size - 1px; //991 px

.secondary {
    background: white !important;
}

.secondary-content {
    padding-top: 140px;
    margin: 0;
}

.xp-auth-box > h3 {
    margin-top: 0;
    margin-bottom: 15px;
}

.text-black {
    color: #2b343a !important;
}

.text-muted {
    color: #8a98ac !important;
}

%button-primary {
    background-color: #346bf1;
    border-color: #346bf1;
}

%button-success {
    background-color: #27b866;
    border-color: #27b866;
}

.btn {
    &-primary {
        &:active { @extend %button-primary; }
        &:hover { @extend %button-primary; }
        &:focus { @extend %button-primary; }
        &:visited { @extend %button-primary; }
        &:not(:disabled):not(.disabled):active {
            @extend %button-primary;
        }
    }

    &-success {
        &:active { @extend %button-success; }
        &:hover { @extend %button-success; }
        &:focus { @extend %button-success; }
        &:visited { @extend %button-success; }
        &:not(:disabled):not(.disabled):active {
            @extend %button-success;
        }
    }
}

.form-control {
    background-color: #f0f1f4;
    font-size: 15px;
    color: #8a98ac;
    border: none;
    border-radius: 5px;

    &:focus {
        background-color: #dbe5fd;
        border-color: none;
        box-shadow: none;

        &:disabled {
            background-color: #e1e4e9;
            opacity: 1;
        }
    }
}

.xp-container {
    .xp-auth-box {
        position: relative;
        //width: 500px;
        width: 700px;
        margin: 0 auto;

        .xp-web-logo img {
            margin: 30px 0;
        }

        @media (max-width: 767px) {
            width: 100%;
        }
    }
}

h5 {
    @media (max-width: $l-size-1) {
        font-size: 16px;
    }

    @media (max-width: $m-size-1) {
        font-size: 15px;
    }
}



.secondary-form-body {
    width: 410px;
    margin: 0 auto;
}

label {
    display: none;
}

.secondary-main-text {
    text-align: left;
}

.form-row {
    padding-bottom: 15px;
}



.tall-line {
    padding-bottom: 20px;
}





</style>
