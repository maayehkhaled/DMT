<div class='test-time-info'>
	<span class='label start-time'>${ test.startTime?datetime?string["${timeStampFormat}"] }</span>
	<span class='label end-time'>${ test.endTime?datetime?string["${timeStampFormat}"] }</span>
	<span class='label time-taken blue lighten-1 white-text'>${ test.getRunDuration()?string }</span>
</div>
<#if test.description?? && test.description?has_content>
<div class='test-desc'>${ test.description} </div>
</#if>
<#if test.hasAuthor() || test.hasCategory()>
<div class='test-attributes'>
	<#if test.hasCategory()>
	<div class='category-list'>
		<#list test.categoryContext.all as category>
		<span class='category label'>${ category.name }</span>
		</#list>
	</div>
	</#if>
	<#if test.hasAuthor()>
	<div class='author-list'>
		<#list test.authorContext.all as author>
		<span class='author label'>${ author.name }</span>
		</#list>
	</div>
	</#if>
	<#if test.hasDevice()>
    <div class='device-list'>
        <#list test.deviceContext.all as device>
        <span class='device label'>${ device.name }</span>
        </#list>
    </div>
    </#if>
</div>
</#if>
<#if test.nodeContext?? && test.nodeContext.all?size != 0>
<div class='test-steps'>
	<table class='bordered table-results'>
		<thead>
			<tr>
                <th>ModuleName</th>
				<th>Status</th>
                <th>Details</th>
                <th>Timestamp</th>
			</tr>
		</thead>
		<tbody>
		<#list test.nodeContext.all as node>
			<tr class='node level-1 log leaf ${ node.status }' status='${ node.status }'>
				<#if node.name??>
                <td class='step-details' class='linked' test-id='${ node.getID() }'>${ node.name }</a></td>
				<#else>
				<td class='step-details'>Step Name Not Defined</td>
			    </#if>
				<td class='status ${ node.status }' status='${ node.status }' title='${ node.status }' alt='${ node.status }'>
					<b style="text-transform: capitalize;">${ node.status }</b>
				</td>
                <td class='step-details'>
                    <#if node.exceptionInfo??>
                    <textarea disabled class="code-block">${node.exceptionInfo.stackTrace}</textarea>
                    <#else>
                    ${node.description}
                    </#if>
					<#if node.logContext.last??>
				<td class='timestamp'>${ node.logContext.last.timestamp?time?string }</td>
				<#else>
				<td class='timestamp'></td>
				</#if>
		    </tr>
			</#list>
	</tbody>
	</table>
</div>

<ul class='collapsible node-list' data-collapsible='accordion'>
	<#macro recurse_nodes nodeList>
	<#list nodeList as node>
	<#assign leaf=(node.hasChildren())?then('leaf','leaf')>
	<li id ='${ node.getID() }' class='node level-${ node.level } ${ leaf } ${ node.status }' status='${ node.status }' test-id='${ node.getID() }'>
		<div class='collapsible-header'>
			<div class='node-name'><i class='material-icons'>view_list</i>${ node.name } - <span class='test-status ${ node.status }'>${ node.status }</span></div>
			<span class='node-time'>${ node.startTime?datetime?string["${timeStampFormat}"] }</span>
			<span class='node-duration'>${ node.runDuration }</span>
			<#if node.hasCategory()>
			<div class='category-list'>
				<#list node.categoryContext.all as category>
				<span class='category label'>${ category.name }</span>
				</#list>
			</div>
			</#if>
		</div>
		<#assign displayContent=true>
		<#if node.getStatus()=='pass' && disableToggleActionForPassedNode=='true'>
		<#assign displayContent=false>
		</#if>
		<#if node.hasLog() && displayContent>
		<div class='collapsible-body'>
			<#if node.hasLog()>
			<#if node.description?? && node.description?has_content>
			<div class='node-desc'>${ node.description}</div>
			</#if>
			<#if node.hasAuthor()>
			<div class='author-list right'>
				<#list node.authorContext.all as author>
				<span class='author label white-text'>${ author.name }</span>
				</#list>
			</div>
			</#if>
			<div class='node-steps'>
				<table class='bordered table-results'>
					<thead>
						<tr>
							<th>Status</th>
							<th>Details</th>
							<th>Timestamp</th>
							<th>ScreenShots</th>
						</tr>
					</thead>
					<tbody>
						<#list node.getLogContext().getAll() as log>
						<tr class='log' status='${ log.status }'>
							<td class='status ${ log.status }' title='${ log.status }' alt='${ log.status }'><i class='material-icons'>${ MaterialIcon.getIcon(log.status) }</i></td>

							<td class='step-details'>
								<#if log.exceptionInfo??>
									<textarea disabled class="code-block">${log.exceptionInfo.stackTrace}</textarea>
								<#else>
									${log.details}
								</#if>
					        </td>
						<td class='timestamp'>${ log.timestamp?time?string }</td>
						<td class='step-details'>
						     <#if log.hasScreenCapture()>${log.screenCaptureContext.last.source}</#if>
							</td>
						</tr>
						</#list>
					</tbody>
				</table>
				<#if node.screenCaptureList?? && node.screenCaptureList?size != 0>
				<ul class='screenshots'>
					<#list node.screenCaptureList as sc>
					<li>${ sc.source }</li>
					</#list>
				</ul>
				</#if>
			</div>
			</#if>
		</div>
		</#if>
		<#if node.hasChildren()>
		<ul class='collapsible node-list' data-collapsible='accordion'>
			<@recurse_nodes nodeList=node.nodeContext.all />
		</ul>
		</#if>
	</li>
	</#list>
	</#macro>
	<@recurse_nodes nodeList=test.nodeContext.all />
</ul>
</#if>
