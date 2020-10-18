<template>
    <div class="modal fade" id="xpLeaveModal" tabindex="-1" role="dialog" aria-labelledby="xpLeaveModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="xp-modalhbar">
                        <form>
                            <h5 class="text-heading">{{ heading }}</h5>
                            <div class="text-subtitle">{{ subtitle }}</div>
                            <button type="button" class="btn btn-danger main-button">{{ mainButton }}</button>
                            <button type="button" class="btn btn-light">{{ cancelButton }}</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "LeaveTeamModal",
    props: {
        heading: String,
        subtitle: String,
        mainButton: String,
        cancelButton: String
    }
}
</script>

<style scoped lang="scss">
.modal-dialog {
    max-width: 700px;
}

.modal-body {
    padding: 40px 60px 40px 60px;
}

$color-heading: #2b343a;
$color-subtitle: #fac751;

form {
    .text-heading {
        color: $color-heading !important;
        padding-bottom: 15px;
    }

    .text-subtitle {
        color: $color-subtitle !important;
        padding-bottom: 80px;
    }
}

.main-button {
    margin-right: 15px;
}

</style>
