(function(){
    
    /**
     * 展示项
     */
    var show = function(item) {
        if($(item).length == 0) {
            return;
        }
        var $totalArea = $(item).closest(".thumb-area");
        var $displayArea = $totalArea.find(".display-area");
        $displayArea.children("canvas").remove();
        var img = $displayArea.children("img");
        img.data("img", null);
        var player = $displayArea.children("video");
        if(!player[0].paused) {
            player[0].pause();
        }
        if(item.hasClass("video-item")) 
        {
            img.hide();
            player.show();
            player.attr("poster", $(item).children("img").attr("src"));
            player.attr("src", $(item).attr("video-path"));
        } else {
            img.show();
            player.hide();
            img.attr("src", $(item).children("img").attr("src"));
        }
        moveLst(item);
        $($totalArea).find(".attachment-lst").find("ul").children(".active").removeClass("active");
        $($($totalArea).find(".attachment-lst").find("ul").children()[$(item).prevAll().length]).addClass("active");
    };
    
    /**
     * 移动列表
     */
    var moveLst = function(item) {
        var $totalArea = $(item).closest(".thumb-area");
        var $divLst = $($totalArea).find(".attachment-lst").children(".img-lst");
        var $ulLst = $divLst.children("ul");
        var overWidth = $divLst.width() - $ulLst.width();
        if(overWidth >= 0) {
            return;
        }
        overWidth = Math.max(overWidth, 0 - $(item).prevAll().length*65);
        $ulLst.animate({
            left: overWidth
        }, "normal", '', function(){
            $ulLst.stop();
        });
    };
    
    /**
     * 警告
     */
    var warn = function($totalArea, str) {
        var $warn = $totalArea.find(".display-area").children(".warning");
        $warn.children("span").html(str);
        $warn.animate({
            opacity: 1
        }, "normal", '', function(){
            setTimeout(function(){
                $warn.css("opacity", 0);
            }, 500);
        });
    }; 
    
    var canvas_enabled = !!document.createElement('canvas').getContext;
    var imgCtrler = function() {
        this.cid = 'imageCtrler';
        this.canvas = null;
        this.maxWidth = 440;
        this.width = 0;
        this.height = 0;
        this.curAngle = 0;
    };
    
    imgCtrler.prototype = {
        init : function(data) {
            var _el = data.el;
            this.width = _el.offsetWidth;
            this.height = _el.offsetHeight;
            if (canvas_enabled) {
                this.canvas = $('<canvas>').attr({
                    'height' : this.height,
                    'width' : this.width
                }).addClass('narrow-move').insertBefore(_el)[0];
                $(_el).hide();
                var ctx = this.canvas.getContext('2d');
            } else {
                var _matrix = 'DXImageTransform.Microsoft.Matrix';
                _el.style.filter = 'progid:DXImageTransform.Microsoft.Matrix()';
                _el.filters.item(_matrix).SizingMethod = "auto expand";
                $(_el).addClass('narrow-move');
                _matrix = null;
            }
            this.element = _el;
        },
        
        rotate : function(dir) {
            if (!this.element) {
                return;
            }
            var _angle, drawW, drawH, h = this.width, w = this.height;
            if (dir === 'right') {
                _angle = this.curAngle + 90;
                this.curAngle = _angle >= 360 ? 0 : _angle;
            } else if (dir === 'left') {
                _angle = this.curAngle - 90;
                this.curAngle = _angle < 0 ? 360 + _angle : _angle;
            } else {
                w = this.width;
                h = this.height;
            }
            _angle = null;
            this.width = w;
            this.height = h;
            if (w > this.maxWidth) {
                h = toInt(this.maxWidth * h / w);
                w = this.maxWidth;
            }
            if (this.canvas) {
                var ctx = this.canvas.getContext('2d'), el = this.element, cpx = 0, cpy = 0;
                $(this.canvas).attr({
                    'width' : w,
                    'height' : h
                });
                ctx.clearRect(0, 0, w, h);
                switch (this.curAngle) {
                case 0:
                    cpx = 0;
                    cpy = 0;
                    drawW = w;
                    drawH = h;
                    break;
                case 90:
                    cpx = w;
                    cpy = 0;
                    drawW = h;
                    drawH = w;
                    break;
                case 180:
                    cpx = w;
                    cpy = h;
                    drawW = w;
                    drawH = h;
                    break;
                case 270:
                    cpx = 0;
                    cpy = h;
                    drawW = h;
                    drawH = w;
                    break;
                }
                ctx.save();
                ctx.translate(cpx, cpy);
                ctx.rotate(this.curAngle * Math.PI / 180);
                ctx.drawImage(el, 0, 0, drawW, drawH);
                ctx.restore();
            } else {
                var _rad = this.curAngle * Math.PI / 180, _cos = Math.cos(_rad), _sin = Math
                        .sin(_rad), _el = this.element, _matrix = 'DXImageTransform.Microsoft.Matrix';
                _el.filters.item(_matrix).M11 = _cos;
                _el.filters.item(_matrix).M12 = -_sin;
                _el.filters.item(_matrix).M21 = _sin;
                _el.filters.item(_matrix).M22 = _cos;
                switch (this.curAngle) {
                case 0:
                case 180:
                    drawW = w;
                    drawH = h;
                    break;
                case 90:
                case 270:
                    drawW = h;
                    drawH = w;
                    break;
                }
                _el.width = drawW;
                _el.height = drawH;
                var _parent = _el.parentNode;
                _parent.style.height = h;
                _parent.style.display = "block";
            }
        }
    };
    
    $.fn.imgRotate = function(dir) {
        this.each(function() {
            if (this.tagName !== 'IMG') {
                return false;
            }
            var img = $(this).data('img');
            if (!img) {
                img = new imgCtrler();
                img.init({
                    el : this
                });
                $(this).data('img', img);
            }
            img.maxWidth = $(this).closest('div').width();
            img.rotate(dir);
        });
        return this;
    };
    
    /**
     * 监听事件
     */
    $(document).on("click", "li.thumb-item", function(){
        var $this = $(this), $totalArea = $(this).closest(".thumb-area"), $playerArea = $totalArea.children(".attachment-player");
        if(!$playerArea.length) 
        {
            $playerArea = $('<div class="attachment-player"/>').appendTo($totalArea).hide();
            
            //  顶部控制栏
            $('<p class="ctrl-box">' +
                 '<a class="hide-up">&nbsp;收起</a>' +
                 '<a class="turn-left">&nbsp;向左转</a>' +
                 '<a class="turn-right">&nbsp;向右转</a>' +
              '</p>').appendTo($playerArea);
            
            //  player播放器
            $('<div class="display-area">' +
                  '<video controls preload="none" width="440" height="330" />' +
                  '<img style="width:440px"/>' +
                  '<p class="warning"><i class="fa fa-exclamation-triangle"></i><span>&nbsp;</span></p>'+

               '</div>').appendTo($playerArea);
            
            //  项目列表
            var attachLst = $('<div class="attachment-lst">' +
                                '<i class="fa fa-chevron-left go-left"></i>' +
                                '<div class="img-lst" />' +
                                '<i class="fa fa-chevron-right go-right"></i>' +
                           '</div>').appendTo($playerArea);
            var imgLst = $this.parent().children().clone().removeClass("thumb-item").addClass(".img-item");
            $("<ul>").addClass("ul-img-lst").width(imgLst.length*65).append(imgLst).appendTo(attachLst.children(".img-lst"));
        }
        show($this);
        $totalArea.children(".thumb-lst").hide();
        $playerArea.show();
        
    }).on("click", "p.ctrl-box>a", function(){
        var $totalArea = $(this).closest(".thumb-area");
        if($(this).hasClass("hide-up")) 
        {
            var player = $totalArea.find("video")[0];
            if(!player.paused) {
                player.pause();
            }
            $totalArea.find(".attachment-player").hide();
            $totalArea.find(".thumb-lst").show();
            return;
        } 
        var $li = $totalArea.find("ul.ul-img-lst").children(".active");
        if($li.hasClass("video-item")) {
            warn($totalArea, "视频无法旋转");
            return;
        }
        
        /*$totalArea.find(".display-area").children("canvas").show();*/
        if($(this).hasClass("turn-left")) {
            $totalArea.find(".display-area").children("img").imgRotate('left');
        } else if($(this).hasClass("turn-right")) {
            $totalArea.find(".display-area").children("img").imgRotate('right');
        }
        /*$totalArea.find(".display-area").children("img").hide();*/
    }).on("click", "ul.ul-img-lst>li", function(){
        show($(this));
    }).on("click", "div.display-area>i", function(){
        var $totalArea = $(this).closest(".thumb-area");
        var $now = $totalArea.find("ul.ul-img-lst").children(".active");
        if($(this).hasClass("go-left")) {
            if($now.prev().length == 0) {
                warn($totalArea, "已经是第一张");
            } else {
                show($now.prev());
            }
        } else {
            if($now.next().length == 0) {
                warn($totalArea, "已经是最后一张");
            } else {
                show($now.next());
            }
        }
    }).on("mousedown", "div.attachment-lst>i", function(){
        if($(this).hasClass("go-left")) 
        {
            var ulLst = $(this).siblings(".img-lst").children("ul");
            ulLst.animate({
                left: 0
            }, "normal", '', function(){
                ulLst.stop();
            });
        } else {
            var ulLst = $(this).siblings(".img-lst").children("ul");
            var overWidth = $(this).siblings(".img-lst").width() - ulLst.width();
            if(overWidth >= 0) {
                return;
            }
            ulLst.animate({
                left: overWidth
            }, "normal", '', function(){
                ulLst.stop();
            });
        }
    }).on("mouseup", "div.attachment-lst>i", function(){
        $(this).siblings(".img-lst").children("ul").stop();
    }).on("mouseover", "div.display-area", function(){
        $(this).children("i").animate({
            opacity: 1
        }, "slow");
    }).on("mouseleave", "div.display-area", function(){
        $(this).children("i").animate({
            opacity: 0
        }, "normal");
    });
    
}());

