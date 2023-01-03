// Considering adding support for passing in v-on or router-link as a parameter to reduce code repetition. Works fine enough now, though.

<template>
    <button class="submit-button">
        <slot>Placeholder</slot>
    </button>
</template>

<script>
export default {
};
</script>

<style>
.submit-button {
    min-width: 160px;
    min-height: 60px;
    border-color: white;
    appearance: none;
    backface-visibility: hidden;
    background-color: #27ae60;
    border-radius: 999px;
    border-style: none;
    box-shadow: rgba(39, 174, 96, 0.15) 0 4px 9px;
    box-sizing: border-box;
    color: #fff;
    cursor: pointer;
    display: inline-block;
    font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    font-size: 1.25rem;
    font-weight: 600;
    line-height: 0;
    outline: none;
    overflow: hidden;
    padding: 25px 30px;
    position: relative;
    text-align: center;
    transform: translate3d(0, 0, 0);
    transition: all 0.3s;
    touch-action: manipulation;
    vertical-align: top;
}

.submit-button:hover {
    background-color: #1e8449;
    opacity: 1;
    transform: translateY(0);
    transition-duration: 0.35s;
}

.submit-button:active {
    transform: translateY(2px);
    transition-duration: 0.35s;
}

.submit-button:hover {
    box-shadow: rgba(39, 174, 96, 0.2) 0 6px 12px;
}
</style>
