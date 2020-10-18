<template>
    <div class="modal search-modal fade" id="xpSearchModal" tabindex="-1" role="dialog" aria-labelledby="xpSearchModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="xp-searchbar">
                        <form onsubmit="return false;">
                            <div class="input-group">
                                <input type="search" class="form-control" placeholder="Search" aria-label="Search" aria-describedby="button-addon2">
                                <div class="input-group-append">
                                    <button class="btn" type="submit" id="button-addon2">{{ caption }}</button>
                                </div>
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
    name: "SearchModal",
    props: {
        caption: String
    }
}
</script>

<style scoped lang="scss">
$color-white: #ffffff;

$color-search-shadow: #c8c8c8;
$color-search-text: #2b343a;
$color-search-button: #4c7cf3;

.search-modal {
    a > i {
        vertical-align: middle;
    }

    .modal-content {
        border: none;
        border-radius: 50px;
        box-shadow: 0 0 30px 0 rgba($color-search-shadow, 0.2);

        .modal-body {
            padding: 5px;
        }

        input[type="search"] {
            background-color: $color-white;
            color: $color-search-text;
            font-size: 18px;
            padding-left: 20px;
            border: none;
            border-radius: 50px 0 0 50px;
        }

        .btn {
            background-color: $color-search-button;
            color: $color-white;
            font-weight: 700;
            font-size: 16px;
            border-radius: 50px;
            padding: 5px 15px 3px 15px;
            box-shadow: none;
        }
    }
}

</style>
