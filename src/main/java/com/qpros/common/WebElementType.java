package com.qpros.common;

public enum WebElementType {

    ANCHOR("a"),
    ASIDE("aside"),
    BODY("body"),
    BUTTON("button"),
    CHECKBOX("checkbox"),
    COMBOBOX("combobox"),
    DATE("date"),
    DEFINITION_DESCRIPTION("dd"),
    DEFINITION_TERM("dt"),
    DIV("div"),
    DL("dl"),
    EMAIL("email"),
    FIELDSET("fieldset"),
    FIG_CAPTION("figcaption"),
    FIGURE("figure"),
    FILE("file"),
    FOOTER("footer"),
    FORM("form"),
    FULL_HEADER("header"),
    HEAD("head"),
    HEADER("^h\\d{1}$"),
    ICON("i"),
    IFRAME("iframe"),
    IMAGE("image"),
    IMG("img"),
    INPUT("input"),
    LABEL("label"),
    LEGEND("legend"),
    LI("li"),
    MAT_FORM_FIELD("mat-form-field"),
    MAT_OPTION("mat-option"),
    MAT_SELECT("mat-select"),
    NAV("nav"),
    NG_APP("ng-app"),
    NG_BIND_HTML("ng-bind-html"),
    NG_BIND_TEMPLATE("ng-bind-template"),
    NG_CONTROLLER("ng-controller"),
    NG_FORM("ng-form"),
    NG_HIDE("ng-hide"),
    NG_INCLUDE("ng-include"),
    NG_JQ("ng-jq"),
    NG_PLURALIZE("ng-pluralize"),
    NG_SHOW("ng-show"),
    NG_TRANSCLUDE_SLOT("ng-transclude-slot"),
    OL("ol"),
    OPTION("option"),
    P_ACCORDIOM("p-accordion"),
    P_AUTO_COMPLETE("p-autoComplete"),
    P_BUTTON("p-button"),
    P_CALENDAR("p-calendar"),
    P_CARD("p-card"),
    P_CAROUSEL("p-carousel"),
    P_CHART("p-chart"),
    P_CHECKBOX("p-checkbox"),
    P_CHIPS("p-chips"),
    P_COLOR_PICKER("p-colorPicker"),
    P_CONFIRM_DIALOG("p-confirmDialog"),
    P_CONTEXT_MENU("p-contextMenu"),
    P_DATA_SCROLLER("p-dataScroller"),
    P_DIALOG("p-dialog"),
    P_DROP_DOWN("p-dropdown"),
    P_EDITOR("p-editor"),
    P_FIELD_SET("p-fieldset"),
    P_FILE_UPLOAD("p-fileUpload"),
    P_G_MAP("p-gmap"),
    P_GALLERIA("p-galleria"),
    P_GROWL("p-growl"),
    P_INPUT_MASK("p-inputMask"),
    P_INPUT_SWITCH("p-inputSwitch"),
    P_LIGHT_BOX("p-lightbox"),
    P_LIST_BOX("p-listbox"),
    P_MEGA_MENU("p-megaMenu"),
    P_MENU("p-menu"),
    P_MENU_BAR("p-menubar"),
    P_MESSAGES("p-messages"),
    P_MULTI_SELECT("p-multiSelect"),
    P_ORDER_LIST("p-orderList"),
    P_ORGANIZATION_CHART("p-organizationChart"),
    P_OVERLAY_PANEL("p-overlayPanel"),
    P_PAGINATOR("p-paginator"),
    P_PANEL("p-panel"),
    P_PANEL_MENU("p-panelMenu"),
    P_PICK_LIST("p-pickList"),
    P_RADIO_BUTTON("p-radioButton"),
    P_RATING("p-rating"),
    P_SCHEDULE("p-schedule"),
    P_SCROLL_PANEL("p-scrollPanel"),
    P_SELECT_BUTTON("p-selectButton"),
    P_SIDE_BAR("p-sidebar"),
    P_SLIDE_MENU("p-slideMenu"),
    P_SLIDER("p-slider"),
    P_SPINNER("p-spinner"),
    P_SPLIT_BUTTON("p-splitButton"),
    P_STEPS("p-steps"),
    P_TAB_MENU("p-tabMenu"),
    P_TAB_VIEW("p-tabView"),
    P_TABLE("p-table"),
    P_TIERED_MENU("p-tieredMenu"),
    P_TOGGLE_BUTTON("p-toggleButton"),
    P_TOOL_BAR("p-toolbar"),
    P_TREE("p-tree"),
    P_TREE_TABLE("p-treeTable"),
    P_TRI_STATE_CHECKBOX("p-triStateCheckbox"),
    PASSWORD("password"),
    RADIO("radio"),
    SCRIPT("script"),
    SEARCH("search"),
    SELECT("select"),
    SPAN("span"),
    SUBMIT("submit"),
    TABLE("table"),
    TBODY("tbody"),
    TD("td"),
    TEXT("text"),
    TEXTAREA("textarea"),
    TH("th"),
    THEAD("thead"),
    TR("tr"),
    UL("ul");

    public String type;

    WebElementType(String t) {
        type = t;
    }

}
