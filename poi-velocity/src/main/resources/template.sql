#foreach($i in $list)
insert into t_pollute_source(`id`, `wname`, `sname`, `mname`, `now`, `cuoshi`, `manzu`, `dname`, `zhenggai`, `guanli`, `jiance`, `dstate`, `tsp`, `pname`, `pstate`)
values('$i.id', '$i.wname', '$i.sname', '$i.mname', '$i.now', '$i.cuoshi', '$i.manzu', '$i.dname', '$i.zhenggai', '$i.guanli', '$i.jiance', $i.dstate, $i.tsp, '$i.pname', $i.pstate);
#end