<template>
    <div id="xp-container" class="xp-container">
        <div class="row secondary-content">
            <div class="col-lg-12">
                <div class="xp-auth-box">
                    <h3 class="text-center mt-0 m-b-15">
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
                            <div id="therest">
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
.secondary {
    background: white !important;
}

.h3, .h4, .h6, h3, h4, h6 {
    font-weight: 700;
}

h3 {
    font-size: 28px;
}

h4 {
    font-size: 24px;
}

h6 {
    font-size: 16px;
}

.m-b-15 {
    margin-bottom: 15px;
}

.vh-100 {
    height: 100vh;
}

.text-black {
    color: #2B343A !important;
}

.text-muted {
    color: #8A98AC !important;
}

.btn {
    border-radius: 3px;
    font-size: 14px;
    padding: 7px 18px;
}

.btn-rounded {
    border-radius: 20px;
}

.btn-primary {
    color: #ffffff;
    background-color: #4c7cf3;
    border-color: #4c7cf3;
    box-shadow: 0 2px 6px 0 rgba(76, 124, 243, 0.5);
}

.btn-success {
    color: #ffffff;
    background-color: #2bcd72;
    border-color: #2bcd72;
    box-shadow: 0 2px 6px 0 rgba(43, 205, 114, 0.5);
}

.btn-primary:active {
    background-color: #346bf1;
    border-color: #346bf1;
}

.btn-primary:hover {
    background-color: #346bf1;
    border-color: #346bf1;
}

.btn-primary:focus {
    background-color: #346bf1;
    border-color: #346bf1;
}

.btn-primary:visited {
    background-color: #346bf1;
    border-color: #346bf1;
}

.btn-primary:not(:disabled):not(.disabled):active {
    background-color: #346bf1;
    border-color: #346bf1;
}

.btn-success:active {
    background-color: #27b866;
    border-color: #27b866;
}

.btn-success:hover {
    background-color: #27b866;
    border-color: #27b866;
}

.btn-success:focus {
    background-color: #27b866;
    border-color: #27b866;
}

.btn-success:visited {
    background-color: #27b866;
    border-color: #27b866;
}

.btn-success:not(:disabled):not(.disabled):active {
    background-color: #27b866;
    border-color: #27b866;
}

.btn-primary:focus {
    box-shadow: 0 0 0 0.2rem #acc2f9;
}

.btn-success:focus {
    box-shadow: 0 0 0 0.2rem #7be3a9;
}

.btn-primary:disabled {
    color: #ffffff;
    background-color: #4c7cf3;
    border-color: #4c7cf3;
}

.btn-success:disabled {
    color: #ffffff;
    background-color: #2bcd72;
    border-color: #2bcd72;
}

.form-control {
    background-color: #f0f1f4;
    font-size: 15px;
    color: #8A98AC;
    border: none;
    border-radius: 5px;
}

.form-control:focus {
    background-color: #dbe5fd;
    border-color: none;
    box-shadow: none;
}

.form-control:focus:disabled {
    background-color: #e1e4e9;
    opacity: 1;
}

.xp-container .xp-auth-box {
    position: relative;
    //width: 500px;
    width: 700px;
    margin: 0 auto;
}

.xp-container .xp-auth-box .xp-web-logo img {
    margin: 30px 0;
}

@media (max-width: 767px) {
    .xp-container .xp-auth-box {
        width: 100%;
    }
}

@media (max-width: 991px) {
    h3 {
        font-size: 26px;
    }

    h4 {
        font-size: 22px;
    }

    h6 {
        font-size: 16px;
    }
}

@media (max-width: 767px) {
    h3 {
        font-size: 24px;
    }

    h4 {
        font-size: 20px;
    }

    h6 {
        font-size: 15px;
    }
}





.secondary-content {
    padding-top: 140px;
    margin: 0;
}

#therest {
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
