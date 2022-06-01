<div class="header navbar">
    <div class="header-container">
        <div class="nav-logo">
          <a href="index.html">
            <div class="logo logo-dark" style="background-image: url('https://cdn.jsdelivr.net/gh/maayehkhaled/quanat-framework/reportlogo.png')"></div>
            <div class="logo logo-white" style="background-image: url('https://cdn.jsdelivr.net/gh/maayehkhaled/quanat-framework/reportlogo.png')"></div>
          </a>
        </div>
        <ul class="nav-left">
          <li>
            <a class="sidenav-fold-toggler" href="javascript:void(0);">
              <i class="fa fa-bars"></i>
            </a>
            <a class="sidenav-expand-toggler" href="javascript:void(0);">
              <i class="fa fa-bars"></i>
            </a>
          </li>
          <li class="search-box">
            <a class="search-toggle" href="javascript:void(0);">
              <i class="search-icon fa fa-search"></i>
              <i class="search-icon-close fa fa-close"></i>
            </a>
          </li>
          <li class="search-input">
            <input id="search-tests" class="form-control" type="text" placeholder="Type to search...">
          </li>
        </ul>
        <ul class="nav-right">
          <li class="m-r-10">
            <a href="#">
              <span class="badge badge-primary">${config.getValue("reportName")}</span>
            </a>
          </li>
            <li class="m-r-10">
                <a href="#">
                    <span class="badge badge-primary">${report.startTime?datetime?string["${timeStampFormat}"]}</span>
                </a>
            </li>
        </ul>
    </div>
</div>
