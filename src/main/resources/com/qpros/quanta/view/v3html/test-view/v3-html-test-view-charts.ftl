<#assign sizeLarge='s12 m12 l12'>
<#if report.reportStatusStats.childCount!=0>
    <#assign sizeLarge='s12 m6 l6'>
</#if>
<#if bddReport || (report.reportStatusStats.childCount != 0 && report.reportStatusStats.grandChildCount != 0)>
    <#assign sizeLarge='s12 m4 l4'>
</#if>
<#assign chartWidth="90" chartHeight="70" chartBoxHeight="94">
<div id='test-view-charts' class='subview-full'>
    <div id='charts-row' class='row nm-v nm-h'>
        <div class='col ${ sizeLarge } np-h'>
            <div class='card-panel nm-v'>
                <div class='left panel-name'>${ parentViewChartsHeading }</div>
                <div class='chart-box' >
                    <div id='parent-analysis' width='${chartWidth}' height='${chartHeight}'></div>
                </div>
                <dl>
                    <dt>
                <div class='strong block text-small'>
                    <span class='Strong ' ><span class='strong'>${ report.reportStatusStats.parentCountPass +report.reportStatusStats.parentCountFail + report.reportStatusStats.parentCountFatal +report.reportStatusStats.parentCountError + report.reportStatusStats.parentCountWarning + report.reportStatusStats.parentCountSkip }</span> ${parentLabel} Total Test Running</span>
                </div>
                    </dt>
                    <dd>
                <div class='block text-small'>
                    <span ><span >${ report.reportStatusStats.parentCountPass }</span> ${parentLabel} Passed (${report.reportStatusStats.parentPercentagePass?string["0.0"]}%)</span>
                </div>
                    </dd>
                    <dd>
                <div class='block text-small'>
                    <span >${ report.reportStatusStats.parentCountFail + report.reportStatusStats.parentCountFatal }</span> ${parentLabel} Failed (${report.reportStatusStats.parentPercentageFail?string["0.0"]}%)
                </div>
                    </dd>
                    <dd>
                <div class='block text-small'>
                    <span>(Error(${ report.reportStatusStats.parentCountError }) ,</span>
                    <span>Warning(${ report.reportStatusStats.parentCountWarning }) ,</span>
                    <span>Skipped(${ report.reportStatusStats.parentCountSkip })  (${report.reportStatusStats.parentPercentageOthers?string["0.0"]}%)</span>
                </div>
                    </dd>
                </dl>
            </div>
        </div>
        <#if report.reportStatusStats.childCount != 0>
        <div class='col ${ sizeLarge } np-h'>
            <div class='card-panel nm-v'>
                <div class='left panel-name'>${ childViewChartsHeading }</div>
                <div class='chart-box' ">
                    <div id='child-analysis' width='${chartWidth}' height='${chartHeight}'></div>
                </div>
                <dl>
                    <dt>
                <div class='strong block text-small'>
                    <span ><span class='strong'>${ report.reportStatusStats.childCountPass +report.reportStatusStats.childCountFail + report.reportStatusStats.childCountFatal +report.reportStatusStats.childCountError +report.reportStatusStats.childCountWarning +report.reportStatusStats.childCountSkip + report.reportStatusStats.childCountInfo }</span> ${childLabel} Total Steps Running</span>
                </div>
                    </dt>
                    <dd>
                <div class='block text-small'>
                    <span>${ report.reportStatusStats.childCountPass }</span> ${childLabel} Passed (${report.reportStatusStats.childPercentagePass?string["0.0"]}%) </span>
                </div>
                    </dd>
                    <dd>
                <div class='block text-small'>
                    <span >${ report.reportStatusStats.childCountFail + report.reportStatusStats.childCountFatal }</span> ${childLabel} Failed ${grandChildLabel} Failed (${report.reportStatusStats.childPercentageFail?string["0.0"]}%)
                </div>
                    </dd>
                    <dd>
                <div class='block text-small'>
                    <span>(Error(${ report.reportStatusStats.childCountError }) ,</span>
                    <span>Warning(${ report.reportStatusStats.childCountWarning }) ,</span>
                    <span>Skipped(${ report.reportStatusStats.childCountSkip  }) ,</span>
                    <span>Info(${report.reportStatusStats.childCountInfo })) (${report.reportStatusStats.childPercentageOthers?string["0.0"]}%)</span>
                </div>
            </dd>
            </dl>
            </div>

        </div>
        </#if>
        <#if report.reportStatusStats.grandChildCount != 0>
        <div class='col ${ sizeLarge } np-h'>
            <div class='card-panel nm-v'>
                <div class='left panel-name'>${ grandChildViewChartsHeading }</div>
                <div class='chart-box' >
                    <div id='grandchild-analysis' width='${chartWidth}' height='${chartHeight}'></div>
                </div>
                <dl>
                    <dt>
                <div class='strong block text-small'>
                    <span  ><span class='strong'>${ report.reportStatusStats.grandChildCountPass +report.reportStatusStats.grandChildCountFail + report.reportStatusStats.grandChildCountFatal +report.reportStatusStats.grandChildCountSkip +report.reportStatusStats.grandChildCountError +report.reportStatusStats.grandChildCountWarning +report.reportStatusStats.grandChildCountInfo }</span> ${childLabel} Total Steps Running</span>
                </div>
                    </dt>
                    <dd>
                <div class='block text-small'>
                    <span class='tooltipped' data-position='top' ><span class='strong'>${ report.reportStatusStats.grandChildCountPass }</span> ${grandChildLabel} Passed (${report.reportStatusStats.grandChildPercentagePass?string["0.0"]}%) </span>
                </div>
                    </dd>
                    <dd>
                <div class='block text-small'>
                    <span class='strong tooltipped' data-position='top' >${ report.reportStatusStats.grandChildCountFail + report.reportStatusStats.grandChildCountFatal }</span> ${grandChildLabel} Failed (${report.reportStatusStats.grandChildPercentageFail?string["0.0"]}%)
                 </div>
                    </dd>
                    <dd>
                 <div class=' block text-small'>
                         <span >(Error(${ report.reportStatusStats.grandChildCountError }) ,</span>
                         <span >Warning(${ report.reportStatusStats.grandChildCountWarning }) ,</span>
                         <span >Skipped(${ report.reportStatusStats.grandChildCountSkip  }) ,</span>
                         <span >Info(${report.reportStatusStats.grandChildCountInfo }))  (${report.reportStatusStats.grandChildPercentageOthers?string["0.0"]}%)</span>
                </div>
                    </dd>
                    </dl>
            </div>
        </div>
        </#if>
    </div>
    <#if enableTimeline=='false'>
    <div id="timeline-chart" class="row nm-v nm-h">
        <div class="col s12 m12 l12 np-h">
            <div class="card-panel">
                <div class='left panel-name'>Timeline (seconds)</div>
                <div class="chart-box" style="width:98%;max-height:145px;">
                    <canvas id="timeline" height="120"></canvas>
                </div>
            </div>
        </div>
    </div>
    </#if>
</div>
