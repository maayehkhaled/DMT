<div id='dashboard-view' class='view hide'>
	<div class='card-panel transparent np-v'>
		<h5>Dashboard</h5>

		<div class='row'>
			<div class='col s2'>
				<div class='card-panel r' style="text-align: center;">
					<b>${ parentViewChartsHeading }</b>
					<div class='panel-lead' style="left: 15px;">${ parentCount }</div>
				</div>
			</div>
			<div class='col s2'>
				<div class='card-panel r' style='text-align: center;'>
					<b>${ childViewChartsHeading }</b>
					<div class='panel-lead' style="left: 15px;">${ childCount  }</div>
				</div>
			</div>
			<#if grandChildViewChartsHeading != ''>
			<div class='col s2'>
				<div class='card-panel r' style='text-align: center;'>
					<b>${ grandChildViewChartsHeading }</b>
					<div class='panel-lead' style="left: 15px;">${ grandChildCount }</div>
				</div>
			</div>
			</#if>
			<div class='col s2'>
				<div class='card-panel r' style="text-align: center;">
					<b>Start</b>
					<div class='panel-lead' style="left: 15px;">${ report.startTime?datetime?string["${timeStampFormat}"] }</div>
				</div>
			</div>
			<div class='col s2'>
				<div class='card-panel r' style="text-align: center;">
					<b>End</b>
			 		<div class='panel-lead' style="left: 15px;">${ report.endTime?datetime?string["${timeStampFormat}"] }</div>
				</div>
			</div>
			<div class='col s2'>
				<div class='card-panel r' style="text-align: center;">
					<b>Time Taken</b>
					<p>HH:MM:SS:MS</p>
					<div class='panel-lead' style="left: 15px;">${ report.longRunDuration }</div>
				</div>
			</div>
			<#if systemAttributeContext?size != 0>
			<div class='col s4' style="text-align: center;">
				<div class='card-panel dashboard-environment'>
					<span class='left label cyan white-text'>Environment</span><p>&nbsp;</p>

					<table>
						<tr>
							<th>Name</th>
							<th>Value</th>
						</tr>
						<#list systemAttributeContext as sa>
							<#if sa?? && sa.name?? && sa.value??>
							<tr>
								<td>${ sa.name }</td>
								<td>${ sa.value }</td>
							</tr>
							</#if>
						</#list>
					</table>
				</div>
			</div>
			</#if>
			<#if categoryContext?? && categoryContext?size != 0>
			<div class='col s6'>
				<div class='card-panel dashboard-categories'>
					<span class='left label cyan white-text'>Categories</span><p>&nbsp;</p>

					<table>
						<tr>
							<th>Suite</th>
							<th>Sub-Suite</th>
							<th>Passed</th>
							<th>Failed</th>
							<th>Others</th>
							<th>Passed %</th>
						</tr>
						<#list report.testList as test>
						<tr>
							<td>${test.name}</td>
							<td>-</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>

							</td>
						</tr>
						<#if test.name?contains("01")>
						<#list categoryContext as category>

						<td></td>
						<td>${category.name}</td>
						<td>${category.passed}</td>
						<td>${category.failed}</td>
						<td>${category.others}</td>
						<td>
							<#if category.size()!=0>
							${((category.passed/category.size())*100)?string["0"]}%
							<#else>
							0%
						</#if>
						</td>
						</tr>
					</#list>
				<#else>
				<#list authorContext as category>

				<td></td>
				<td>${category.name}</td>
				<td>${category.passed}</td>
				<td>${category.failed}</td>
				<td>${category.others}</td>
				<td>
					<#if category.size()!=0>
					${((category.passed/category.size())*100)?string["0"]}%
					<#else>
					0%
				</#if>
				</td>
				</tr>
			</#list>

			</#if>

				</#list>
					</table>
				</div>
			</div>
			</#if>
		</div>
	</div>
</div>
<!-- dashboard view -->
