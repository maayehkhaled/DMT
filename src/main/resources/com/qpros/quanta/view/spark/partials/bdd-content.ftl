<#macro stepdetails test>
	<#if test.hasLog()>
		<#list test.logContext.all as log>
			<#if log.exceptionInfo??>
				<textarea disabled class="code-block">${log.exceptionInfo.stackTrace}</textarea>
			<#else>
				<div class="details">${log.details}</div>
			</#if>
			<#if log.hasScreenCapture()>
				<span class="badge badge-primary">${log.screenCaptureContext.last.source}</span>
			</#if>
		</#list>
	</#if>
</#macro>

<#if test.hasChildren()>
<div class="accordion mt-4">
	<#list test.nodeContext.all as node>
	<div class="card">
		<div class="card-header" role="tab">
			<h5 class="card-title pl-3">
				<div class="node">${node.name}</div>
				<div class="status-avatar float-left ${node.status}-bg">
					<i class="fa fa-${Icon.getIcon(node.status)} text-white"></i>
				</div>
			</h5>
		</div>
		<#if node.hasChildren()>
			<#if node.behaviorDrivenTypeName=="Scenario Outline">
				<div class="collapse scenario_outline">
					<#list node.nodeContext.all as child>
						<div class="card-body l1">
							<div class="card-header" role="tab">
								<h5 class="card-title outline-child">
									<div class="node">${child.name}</div>
									<div class="status-avatar float-left ${child.status}-bg">
										<i class="fa fa-${Icon.getIcon(child.status)} text-white"></i>
									</div>
								</h5>
							</div>
							<div class="card-body collapse mt-3">
								<#list child.nodeContext.all as step>
									<div class="d-flex align-items-center justify-content-start ${step.behaviorDrivenTypeName?replace(' ','')?replace('*','asterisk')?lower_case}" title="${step.description}">
										<span class="alert-icon ${step.status}-bg">
											<i class="fa fa-${Icon.getIcon(step.status)} text-white"></i>
										</span>
										<span>${step.name}</span>
									</div>
									<@stepdetails test=step />
								</#list>
							</div>
						</div>
					</#list>
				</div>
			<#else>
				<div class="collapse">
					<div class="card-body">
						<#list node.nodeContext.all as child>
							<div class="d-flex align-items-center justify-content-start ${child.behaviorDrivenTypeName?replace(' ','')?replace('*','asterisk')?lower_case}" title="${child.description}">
								<span class="alert-icon ${child.status}-bg">
									<i class="fa fa-${Icon.getIcon(child.status)} text-white"></i>
								</span>
								<span>${child.name}</span>
							</div>
							<@stepdetails test=child />
						</#list>
					</div>
				</div>
			</#if>
		</#if>
	</div>
	</#list>
</div>
</#if>