/*
 * jQuery PNotify Plugin 1.3.1
 *
 * http://sciactive.com/pnotify/
 * Copyright (c) 2009-2014 Hunter Perrin
 *
 * Triple license under the GPL, LGPL, and MPL:
 *	  http://www.gnu.org/licenses/gpl.html
 *	  http://www.gnu.org/licenses/lgpl.html
 *	  http://www.mozilla.org/MPL/MPL-1.1.html
 */
(function(d){"function"===typeof define&&define.amd?define(["jquery"],d):d(jQuery)})(function(d){var v,m,n,k=d(window),l={jqueryui:{container:"ui-widget ui-widget-content ui-corner-all",notice:"ui-state-highlight",notice_icon:"ui-icon ui-icon-info",info:"",info_icon:"ui-icon ui-icon-info",success:"ui-state-default",success_icon:"ui-icon ui-icon-circle-check",error:"ui-state-error",error_icon:"ui-icon ui-icon-alert",closer:"ui-icon ui-icon-close",pin_up:"ui-icon ui-icon-pin-w",pin_down:"ui-icon ui-icon-pin-s",
hi_menu:"ui-state-default ui-corner-bottom",hi_btn:"ui-state-default ui-corner-all",hi_btnhov:"ui-state-hover",hi_hnd:"ui-icon ui-icon-grip-dotted-horizontal"},bootstrap:{container:"alert",notice:"",notice_icon:"icon-exclamation-sign",info:"alert-info",info_icon:"icon-info-sign",success:"alert-success",success_icon:"icon-ok-sign",error:"alert-error",error_icon:"icon-warning-sign",closer:"icon-remove",pin_up:"icon-pause",pin_down:"icon-play",hi_menu:"well",hi_btn:"btn",hi_btnhov:"",hi_hnd:"icon-chevron-down"},
bootstrap3:{container:"alert",notice:"alert-warning",notice_icon:"glyphicon glyphicon-exclamation-sign",info:"alert-info",info_icon:"glyphicon glyphicon-info-sign",success:"alert-success",success_icon:"glyphicon glyphicon-ok-sign",error:"alert-danger",error_icon:"glyphicon glyphicon-warning-sign",closer:"glyphicon glyphicon-remove",pin_up:"glyphicon glyphicon-pause",pin_down:"glyphicon glyphicon-play",hi_menu:"well",hi_btn:"btn btn-default",hi_btnhov:"",hi_hnd:"glyphicon glyphicon-chevron-down"}};
l.fontawesome=d.extend({},l.bootstrap3);l.fontawesome.notice_icon="fa fa-exclamation-circle";l.fontawesome.info_icon="fa fa-info";l.fontawesome.success_icon="fa fa-check";l.fontawesome.error_icon="fa fa-warning";l.fontawesome.closer="fa fa-times";l.fontawesome.pin_up="fa fa-pause";l.fontawesome.pin_down="fa fa-play";l.fontawesome.hi_hnd="fa fa-chevron-down";var w=function(){n=d("body");d.pnotify.defaults.stack.context=n;k=d(window);k.bind("resize",function(){m&&clearTimeout(m);m=setTimeout(function(){d.pnotify_position_all(!0)},
10)})};d.extend({pnotify_remove_all:function(){var g=k.data("pnotify");g&&g.length&&d.each(g,function(){this.pnotify_remove&&this.pnotify_remove()})},pnotify_position_all:function(g){m&&clearTimeout(m);m=null;var e=k.data("pnotify");e&&e.length&&(d.each(e,function(){var a=this.opts.stack;a&&(a.nextpos1=a.firstpos1,a.nextpos2=a.firstpos2,a.addpos2=0,a.animation=g)}),d.each(e,function(){this.pnotify_position()}))},pnotify:function(g){var e,a;"object"!==typeof g?(a=d.extend({},d.pnotify.defaults),a.text=
g):a=d.extend({},d.pnotify.defaults,g);for(var u in a)"string"===typeof u&&u.match(/^pnotify_/)&&(a[u.replace(/^pnotify_/,"")]=a[u]);if(a.before_init&&!1===a.before_init(a))return null;var p,t=function(a,c){b.css("display","none");var f=document.elementFromPoint(a.clientX,a.clientY);b.css("display","block");var e=d(f),g=e.css("cursor");b.css("cursor","auto"!==g?g:"default");p&&p.get(0)==f||(p&&(s.call(p.get(0),"mouseleave",a.originalEvent),s.call(p.get(0),"mouseout",a.originalEvent)),s.call(f,"mouseenter",
a.originalEvent),s.call(f,"mouseover",a.originalEvent));s.call(f,c,a.originalEvent);p=e},f;f="object"===typeof a.styling?a.styling:l[a.styling];var b=d("<div />",{"class":"ui-pnotify "+a.addclass,css:{display:"none"},mouseenter:function(q){a.nonblock&&q.stopPropagation();a.mouse_reset&&"out"===e&&(b.stop(!0),e="in",b.css("height","auto").animate({width:a.width,opacity:a.nonblock?a.nonblock_opacity:a.opacity},"fast"));a.nonblock&&b.stop().animate({opacity:a.nonblock_opacity},"fast");a.hide&&a.mouse_reset&&
b.pnotify_cancel_remove();a.sticker&&!a.nonblock&&b.sticker.trigger("pnotify_icon").css("visibility","visible");a.closer&&!a.nonblock&&b.closer.css("visibility","visible")},mouseleave:function(q){a.nonblock&&q.stopPropagation();p=null;b.css("cursor","auto");a.nonblock&&"out"!==e&&b.stop().animate({opacity:a.opacity},"fast");a.hide&&a.mouse_reset&&b.pnotify_queue_remove();a.sticker_hover&&b.sticker.css("visibility","hidden");a.closer_hover&&b.closer.css("visibility","hidden");d.pnotify_position_all()},
mouseover:function(b){a.nonblock&&b.stopPropagation()},mouseout:function(b){a.nonblock&&b.stopPropagation()},mousemove:function(b){a.nonblock&&(b.stopPropagation(),t(b,"onmousemove"))},mousedown:function(b){a.nonblock&&(b.stopPropagation(),b.preventDefault(),t(b,"onmousedown"))},mouseup:function(b){a.nonblock&&(b.stopPropagation(),b.preventDefault(),t(b,"onmouseup"))},click:function(b){a.nonblock&&(b.stopPropagation(),t(b,"onclick"))},dblclick:function(b){a.nonblock&&(b.stopPropagation(),t(b,"ondblclick"))}});
b.opts=a;b.container=d("<div />",{"class":f.container+" ui-pnotify-container "+("error"===a.type?f.error:"info"===a.type?f.info:"success"===a.type?f.success:f.notice)}).appendTo(b);""!==a.cornerclass&&b.container.removeClass("ui-corner-all").addClass(a.cornerclass);a.shadow&&b.container.addClass("ui-pnotify-shadow");b.pnotify_version="1.3.1";b.pnotify=function(q){var c=a;"string"===typeof q?a.text=q:a=d.extend({},a,q);for(var e in a)"string"===typeof e&&e.match(/^pnotify_/)&&(a[e.replace(/^pnotify_/,
"")]=a[e]);b.opts=a;a.cornerclass!==c.cornerclass&&b.container.removeClass("ui-corner-all").addClass(a.cornerclass);a.shadow!==c.shadow&&(a.shadow?b.container.addClass("ui-pnotify-shadow"):b.container.removeClass("ui-pnotify-shadow"));!1===a.addclass?b.removeClass(c.addclass):a.addclass!==c.addclass&&b.removeClass(c.addclass).addClass(a.addclass);!1===a.title?b.title_container.slideUp("fast"):a.title!==c.title&&(a.title_escape?b.title_container.text(a.title).slideDown(200):b.title_container.html(a.title).slideDown(200));
!1===a.text?b.text_container.slideUp("fast"):a.text!==c.text&&(a.text_escape?b.text_container.text(a.text).slideDown(200):b.text_container.html(a.insert_brs?String(a.text).replace(/\n/g,"<br />"):a.text).slideDown(200));b.pnotify_history=a.history;b.pnotify_hide=a.hide;a.type!==c.type&&b.container.removeClass(f.error+" "+f.notice+" "+f.success+" "+f.info).addClass("error"===a.type?f.error:"info"===a.type?f.info:"success"===a.type?f.success:f.notice);if(a.icon!==c.icon||!0===a.icon&&a.type!==c.type)b.container.find("div.ui-pnotify-icon").remove(),
!1!==a.icon&&d("<div />",{"class":"ui-pnotify-icon"}).append(d("<span />",{"class":!0===a.icon?"error"===a.type?f.error_icon:"info"===a.type?f.info_icon:"success"===a.type?f.success_icon:f.notice_icon:a.icon})).prependTo(b.container);a.width!==c.width&&b.animate({width:a.width});a.min_height!==c.min_height&&b.container.animate({minHeight:a.min_height});a.opacity!==c.opacity&&b.fadeTo(a.animate_speed,a.opacity);!a.closer||a.nonblock?b.closer.css("display","none"):b.closer.css("display","block");!a.sticker||
a.nonblock?b.sticker.css("display","none"):b.sticker.css("display","block");b.sticker.trigger("pnotify_icon");a.sticker_hover?b.sticker.css("visibility","hidden"):a.nonblock||b.sticker.css("visibility","visible");a.closer_hover?b.closer.css("visibility","hidden"):a.nonblock||b.closer.css("visibility","visible");a.hide?c.hide||b.pnotify_queue_remove():b.pnotify_cancel_remove();b.pnotify_queue_position(!0);return b};b.pnotify_position=function(a){var c=b.opts.stack;"undefined"===typeof c.context&&(c.context=
n);if(c){"number"!==typeof c.nextpos1&&(c.nextpos1=c.firstpos1);"number"!==typeof c.nextpos2&&(c.nextpos2=c.firstpos2);"number"!==typeof c.addpos2&&(c.addpos2=0);var d="none"===b.css("display");if(!d||a){var f,e={},g;switch(c.dir1){case "down":g="top";break;case "up":g="bottom";break;case "left":g="right";break;case "right":g="left"}a=parseInt(b.css(g).replace(/(?:\..*|[^0-9.])/g,""));isNaN(a)&&(a=0);"undefined"!==typeof c.firstpos1||d||(c.firstpos1=a,c.nextpos1=c.firstpos1);var h;switch(c.dir2){case "down":h=
"top";break;case "up":h="bottom";break;case "left":h="right";break;case "right":h="left"}f=parseInt(b.css(h).replace(/(?:\..*|[^0-9.])/g,""));isNaN(f)&&(f=0);"undefined"!==typeof c.firstpos2||d||(c.firstpos2=f,c.nextpos2=c.firstpos2);if("down"===c.dir1&&c.nextpos1+b.height()>(c.context.is(n)?k.height():c.context.prop("scrollHeight"))||"up"===c.dir1&&c.nextpos1+b.height()>(c.context.is(n)?k.height():c.context.prop("scrollHeight"))||"left"===c.dir1&&c.nextpos1+b.width()>(c.context.is(n)?k.width():c.context.prop("scrollWidth"))||
"right"===c.dir1&&c.nextpos1+b.width()>(c.context.is(n)?k.width():c.context.prop("scrollWidth")))c.nextpos1=c.firstpos1,c.nextpos2+=c.addpos2+("undefined"===typeof c.spacing2?25:c.spacing2),c.addpos2=0;if(c.animation&&c.nextpos2<f)switch(c.dir2){case "down":e.top=c.nextpos2+"px";break;case "up":e.bottom=c.nextpos2+"px";break;case "left":e.right=c.nextpos2+"px";break;case "right":e.left=c.nextpos2+"px"}else"number"===typeof c.nextpos2&&b.css(h,c.nextpos2+"px");switch(c.dir2){case "down":case "up":b.outerHeight(!0)>
c.addpos2&&(c.addpos2=b.height());break;case "left":case "right":b.outerWidth(!0)>c.addpos2&&(c.addpos2=b.width())}if("number"===typeof c.nextpos1)if(c.animation&&(a>c.nextpos1||e.top||e.bottom||e.right||e.left))switch(c.dir1){case "down":e.top=c.nextpos1+"px";break;case "up":e.bottom=c.nextpos1+"px";break;case "left":e.right=c.nextpos1+"px";break;case "right":e.left=c.nextpos1+"px"}else b.css(g,c.nextpos1+"px");(e.top||e.bottom||e.right||e.left)&&b.animate(e,{duration:this.opts.position_animate_speed,
queue:!1});switch(c.dir1){case "down":case "up":c.nextpos1+=b.height()+("undefined"===typeof c.spacing1?25:c.spacing1);break;case "left":case "right":c.nextpos1+=b.width()+("undefined"===typeof c.spacing1?25:c.spacing1)}}}};b.pnotify_queue_position=function(a,b){m&&clearTimeout(m);b||(b=10);m=setTimeout(function(){d.pnotify_position_all(a)},b)};b.pnotify_display=function(){if((h=k.data("pnotify"))&&h.length>a.maxonscreen){var e;e="top"!==a.stack.push?h.slice(0,h.length-a.maxonscreen):h.slice(a.maxonscreen,
h.length);d.each(e,function(){this.pnotify_remove&&this.pnotify_remove()})}b.parent().length||b.appendTo(a.stack.context?a.stack.context:n);a.before_open&&!1===a.before_open(b)||("top"!==a.stack.push&&b.pnotify_position(!0),"fade"===a.animation||"fade"===a.animation.effect_in?b.show().fadeTo(0,0).hide():1!==a.opacity&&b.show().fadeTo(0,a.opacity).hide(),b.animate_in(function(){a.after_open&&a.after_open(b);b.pnotify_queue_position(!0);a.hide&&b.pnotify_queue_remove()}))};b.pnotify_remove=function(e){b.timer&&
(window.clearTimeout(b.timer),b.timer=null);a.before_close&&!1===a.before_close(b,e)||b.animate_out(function(){if(!a.after_close||!1!==a.after_close(b,e))if(b.pnotify_queue_position(!0),a.remove&&b.detach(),!a.history){var c=k.data("pnotify");if(null!==c){var f=d.inArray(b,c);-1!==f&&c.splice(f,1)}}})};b.animate_in=function(d){e="in";var c;c="undefined"!==typeof a.animation.effect_in?a.animation.effect_in:a.animation;"none"===c?(b.show(),d()):"show"===c?b.show(a.animate_speed,d):"fade"===c?b.show().fadeTo(a.animate_speed,
a.opacity,d):"slide"===c?b.slideDown(a.animate_speed,d):"function"===typeof c?c("in",d,b):b.show(c,"object"===typeof a.animation.options_in?a.animation.options_in:{},a.animate_speed,d)};b.animate_out=function(d){e="out";var c;c="undefined"!==typeof a.animation.effect_out?a.animation.effect_out:a.animation;"none"===c?(b.hide(),d()):"show"===c?b.hide(a.animate_speed,d):"fade"===c?b.fadeOut(a.animate_speed,d):"slide"===c?b.slideUp(a.animate_speed,d):"function"===typeof c?c("out",d,b):b.hide(c,"object"===
typeof a.animation.options_out?a.animation.options_out:{},a.animate_speed,d)};b.pnotify_cancel_remove=function(){b.timer&&window.clearTimeout(b.timer)};b.pnotify_queue_remove=function(){b.pnotify_cancel_remove();b.timer=window.setTimeout(function(){b.pnotify_remove(!0)},isNaN(a.delay)?0:a.delay)};b.closer=d("<div />",{"class":"ui-pnotify-closer",css:{cursor:"pointer",visibility:a.closer_hover?"hidden":"visible"},click:function(){b.pnotify_remove(!1);b.sticker.css("visibility","hidden");b.closer.css("visibility",
"hidden")}}).append(d("<span />",{"class":f.closer,title:a.labels.close})).appendTo(b.container);a.closer&&!a.nonblock||b.closer.css("display","none");b.sticker=d("<div />",{"class":"ui-pnotify-sticker",css:{cursor:"pointer",visibility:a.sticker_hover?"hidden":"visible"},click:function(){a.hide=!a.hide;a.hide?b.pnotify_queue_remove():b.pnotify_cancel_remove();d(this).trigger("pnotify_icon")}}).bind("pnotify_icon",function(){d(this).children().removeClass(f.pin_up+" "+f.pin_down).addClass(a.hide?f.pin_up:
f.pin_down)}).append(d("<span />",{"class":f.pin_up,title:a.labels.stick})).appendTo(b.container);a.sticker&&!a.nonblock||b.sticker.css("display","none");!1!==a.icon&&d("<div />",{"class":"ui-pnotify-icon"}).append(d("<span />",{"class":!0===a.icon?"error"===a.type?f.error_icon:"info"===a.type?f.info_icon:"success"===a.type?f.success_icon:f.notice_icon:a.icon})).prependTo(b.container);b.title_container=d("<h4 />",{"class":"ui-pnotify-title"}).appendTo(b.container);!1===a.title?b.title_container.hide():
a.title_escape?b.title_container.text(a.title):b.title_container.html(a.title);b.text_container=d("<div />",{"class":"ui-pnotify-text"}).appendTo(b.container);!1===a.text?b.text_container.hide():a.text_escape?b.text_container.text(a.text):b.text_container.html(a.insert_brs?String(a.text).replace(/\n/g,"<br />"):a.text);"string"===typeof a.width&&b.css("width",a.width);"string"===typeof a.min_height&&b.container.css("min-height",a.min_height);b.pnotify_history=a.history;b.pnotify_hide=a.hide;var h=
k.data("pnotify");if(null===h||"object"!==typeof h)h=[];h="top"===a.stack.push?d.merge([b],h):d.merge(h,[b]);k.data("pnotify",h);"top"===a.stack.push&&b.pnotify_queue_position(!1,1);a.after_init&&a.after_init(b);if(a.history){var r=k.data("pnotify_history");"undefined"===typeof r&&(d("body").on("pnotify.history-all",function(){d.each(h,function(){this.pnotify_history&&(this.is(":visible")?this.pnotify_hide&&this.pnotify_queue_remove():this.pnotify_display&&this.pnotify_display())})}).on("pnotify.history-last",
function(){var a="top"===d.pnotify.defaults.stack.push,b=a?0:-1,e;do{e=-1===b?h.slice(b):h.slice(b,b+1);if(!e[0])return!1;b=a?b+1:b-1}while(!e[0].pnotify_history||e[0].is(":visible"));e[0].pnotify_display&&e[0].pnotify_display()}),r=d("<div />",{"class":"ui-pnotify-history-container "+f.hi_menu,mouseleave:function(){r.animate({top:"-"+v+"px"},{duration:100,queue:!1})}}).append(d("<div />",{"class":"ui-pnotify-history-header",text:a.labels.redisplay})).append(d("<button />",{"class":"ui-pnotify-history-all "+
f.hi_btn,text:a.labels.all,mouseenter:function(){d(this).addClass(f.hi_btnhov)},mouseleave:function(){d(this).removeClass(f.hi_btnhov)},click:function(){d(this).trigger("pnotify.history-all");return!1}})).append(d("<button />",{"class":"ui-pnotify-history-last "+f.hi_btn,text:a.labels.last,mouseenter:function(){d(this).addClass(f.hi_btnhov)},mouseleave:function(){d(this).removeClass(f.hi_btnhov)},click:function(){d(this).trigger("pnotify.history-last");return!1}})).appendTo(n),v=d("<span />",{"class":"ui-pnotify-history-pulldown "+
f.hi_hnd,mouseenter:function(){r.animate({top:"0"},{duration:100,queue:!1})}}).appendTo(r).offset().top+2,r.css({top:"-"+v+"px"}),k.data("pnotify_history",r))}a.stack.animation=!1;a.auto_display&&b.pnotify_display();return b}});var x=/^on/,y=/^(dbl)?click$|^mouse(move|down|up|over|out|enter|leave)$|^contextmenu$/,z=/^(focus|blur|select|change|reset)$|^key(press|down|up)$/,A=/^(scroll|resize|(un)?load|abort|error)$/,s=function(g,e){var a;g=g.toLowerCase();document.createEvent&&this.dispatchEvent?(g=
g.replace(x,""),g.match(y)?(d(this).offset(),a=document.createEvent("MouseEvents"),a.initMouseEvent(g,e.bubbles,e.cancelable,e.view,e.detail,e.screenX,e.screenY,e.clientX,e.clientY,e.ctrlKey,e.altKey,e.shiftKey,e.metaKey,e.button,e.relatedTarget)):g.match(z)?(a=document.createEvent("UIEvents"),a.initUIEvent(g,e.bubbles,e.cancelable,e.view,e.detail)):g.match(A)&&(a=document.createEvent("HTMLEvents"),a.initEvent(g,e.bubbles,e.cancelable)),a&&this.dispatchEvent(a)):(g.match(x)||(g="on"+g),a=document.createEventObject(e),
this.fireEvent(g,a))};d.pnotify.defaults={title:!1,title_escape:!1,text:!1,text_escape:!1,styling:"bootstrap",addclass:"",cornerclass:"",nonblock:!1,nonblock_opacity:0.2,history:!0,maxonscreen:Infinity,auto_display:!0,width:"300px",min_height:"16px",type:"notice",icon:!0,animation:"fade",animate_speed:"slow",position_animate_speed:500,opacity:1,shadow:!0,closer:!0,closer_hover:!0,sticker:!0,sticker_hover:!0,hide:!0,delay:8E3,mouse_reset:!0,remove:!0,insert_brs:!0,stack:{dir1:"down",dir2:"left",push:"bottom",
spacing1:25,spacing2:25,context:d("body")},labels:{redisplay:"Redisplay",all:"All",last:"Last",close:"Close",stick:"Stick"}};document.body?w():d(w)});