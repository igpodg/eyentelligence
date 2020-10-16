<template>
    <div :class="'card bg-' + color + '-gradient m-b-30'">
        <div class="card-body">
            <div class="xp-widget-box text-white">
                <div class="align-self-center">
                    <p class="xp-icon-timer">
                        <i class="icon-paper-plane"></i>
                    </p>
                    <h4 class="mb-0 font-26">{{ header }}</h4>
                    <p class="subtitle">{{ subtitle }}</p>
                    <div class="col-5 mb-3 team-selector">
                        <select class="form-control"
                                style="margin-right: 20px;">
                            <option disabled selected>Select a team</option>
                            <option v-for="teamName in teamNames" :key="teamName">{{ teamName }}</option>
                        </select>
                    </div>
                    <div>
                        <div v-for="i in emailsDisplayedComputed" :key="i" class="input-group"
                            :style="(i !== 1) ? 'padding-top: 10px;' : ''">
                        <input type="search" class="form-control" :placeholder="'Email #' + i"
                                   aria-label="Search" aria-describedby="button-addon-news"
                                   style="border-top-right-radius: 0; border-bottom-right-radius: 0;">
                            <div class="input-group-append">
                                <button class="btn btn-remove" type="submit" :id="'button-addon-news' + (i+1)"
                                        @click="tempRemove">
                                    &mdash;
                                </button>
                            </div>
                        </div>
                        <button type="button" class="btn btn-rounded btn-facebook"
                                @click="addMore"
                                style="margin-top: 10px;">
                            <i class="icon-plus"></i>&nbsp;More
                        </button>
                        <div style="text-align: center; margin-bottom: 10px;">
                            <button class="mt-4 btn btn-white btn-rounded text-success">Send
                                invites
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "CardInvite",
    props: {
        color: {
            type: String,
            default: "primary"
        },
        header: String,
        subtitle: String,
        teamNames: Array,
        emailsDisplayed: {
            type: Number,
            default: 2
        }
    },
    data: function() {
        return {
            emailsDisplayedComputed: this.emailsDisplayed
        }
    },
    methods: {
        addMore: function() {
            this.emailsDisplayedComputed++;
        },
        tempRemove: function() {
            if (this.emailsDisplayedComputed > 1)
                this.emailsDisplayedComputed--;
        }
    }
}
</script>

<style scoped lang="scss">
.h4, h4 {
    font-weight: 700;
}

h4 {
    font-size: 24px;
}

.m-b-30 {
    margin-bottom: 30px;
}

.font-26 {
    font-size: 26px;
}

.bg-primary-gradient {
    background-image: linear-gradient(58deg, #4c7cf3 0, #4cc6f3 100%);
}

.bg-success-gradient {
    background-image: linear-gradient(58deg, #2bcd72 0, #9acd2b 100%);
}

.bg-danger-gradient {
    background-image: linear-gradient(58deg, #ff4b5b 0, #ff7e4b 100%);
}

.text-white {
    color: #ffffff !important;
}

.text-success {
    color: #2bcd72 !important;
}

a.text-success:focus {
    color: #27b866 !important;
}
a.text-success:hover {
    color: #27b866 !important;
}

.btn {
    border-radius: 3px;
    font-size: 14px;
    padding: 7px 18px;
}

.btn-rounded {
    border-radius: 20px;
}

.btn-white {
    color: #2B343A;
    background-color: #ffffff;
    border-color: #ffffff;
    box-shadow: 0 2px 6px 0 rgba(255, 255, 255, 0.5);
}

.btn-facebook {
    color: #ffffff !important;
    background-color: #3b5998;
}

.card {
    border: none;
    box-shadow: 0 0 30px 0 rgba(200, 200, 200, 0.2);
    border-radius: 15px;
}

.xp-icon-timer i {
    width: 30px;
    height: 30px;
    padding: 15px;
    font-size: 20px;
    border-radius: 50px;
    background-color: rgba(0, 0, 0, 0.2);
}

.form-control {
    //background-color: #f0f1f4;
    font-size: 15px;
    color: #8A98AC;
    border: none;
    //border-radius: 5px;

    background-color: #ffffff !important;
    border-radius: 50px;
}

.form-control:focus {
    background-color: #dbe5fd;
    //border-color: none;
    box-shadow: none;
}

.form-control:focus:disabled {
    background-color: #e1e4e9;
    opacity: 1;
}

@media (max-width: 991px) {
    h4 {
        font-size: 22px;
    }
}

@media (max-width: 767px) {
    h4 {
        font-size: 20px;
    }
}





.align-self-center {
    padding-left: 10px;
    padding-right: 10px;
}

.xp-icon-timer {
    margin-top: 20px;
    margin-bottom: 25px;
}

.subtitle {
    padding-bottom: 16px;
}

.team-selector {
    padding-bottom: 20px;
    padding-left: 0;
}

.input-group-append {
    padding: 3px 3px 2px 0;
    background: #ffffff;
    border-radius: 0 50px 50px 0;
}

.btn-remove {
    border-radius: 50px !important;
    background-color: #ff4b5b;
    color: #ffffff;
    padding: 4px 10px;
    font-weight: 700;
}

</style>
