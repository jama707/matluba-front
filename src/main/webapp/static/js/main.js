/**
 * Created by jasurbek.umarov on 11/26/2014.
 */

$(function () {
    return
    var infoSelectors = ['js-company-phone', 'js-company-address', 'js-company-email'];
    $.ajax({ success: function( response ){
        $.each(infoSelectors, function (idx, selector) {
            var prefixIndex = selector.indexOf('js-company-');
            var fieldSelector = selector.substr(prefixIndex);
            $("." + selector).text(response[fieldSelector]);
        });
    }});
});

$(function () {
    return;
    var $productController = $(".js-product-controller");
    if (!$productController.length) return;
    
    var settings = {
        pagination: { snap: 7 }
    }

    function update() {
        $.ajax({
            success: function (response) {
                var products = response.products || [];
                var pages = response.pages || 0;
                var $listing = $productController.find(".js-product-listing");
                var $template = $('#productTemplate');
                var $products = [];

                $.each(products, function (idx, product) {
                    var $clone = $template.clone();
                    $clone.find(".js-product-image").attr();
                    $clone.find(".js-product-title").attr();
                    $products.push($clone);
                });

                $listing.empty().append($products);
            }
        });

        $(".js-paginator").pagination({ total: 23, active: 1 });
    }
});


// PLUGINS
+function () {
    'use strict';

    var defaultOptions = {
        snap: 6,
        active: 0,
        total: 0
    };

    var Pagination = function (element, options) {
        this.$element = $(element);
        this.init($.extend({}, defaultOptions, options));
    }

    Pagination.prototype = {

        init: function (options) {
            var op = this.options = options;
            $.each(this.options, function (field, value) {
                op[field] = normalizeNumber(value);
            });

            setActive(this.options.active, this.options, this.$element);
        },

        next: function () {
            this.setActive(this.options.active + 1);
        },

        prev: function () {
            this.setActive(this.options.active - 1);
        },

        setActive: function (page) {
            if (page !== this.options.active) {
                setActive(page, this.options, this.$element);
            }
        }
    }

    function setActive(page, options, $element) {
        page = normalizeNumber(page);
        // overflow
        options.active = (options.total - page) < 0 ? page = options.total : page;
        
        draw(options, $element);

        $element.trigger($.Event('changed.mb.pagination'));
    }

    function draw(options, $element) {
        var max,
            min,
            snapCount,
            $pagination,
            snap = options.snap,
            active = options.active,
            total = options.total,
            pageBuilder = function (text, index) {
                return $('<li><a href="#">' + text + '</a></li>').addClass('page-btn').attr('data-page-index', index);
            };

        snapCount = !snap ? 0 : Math.ceil(active / snap);
        min = Math.max(snapCount - 1, 0) * snap;
        max = Math.min(total, snapCount * snap);

        $pagination = $element
                        .hide()
                        .empty()
                        .html('<ul class="pagination"></ul>')
                        .find(".pagination");

        var p, idx = min + 1;
        for (; idx < max + 1; idx++) {
            p = pageBuilder(idx, idx);
            if (idx === active) p.addClass('active');
            $pagination.append(p);
        }

        if (p) {
            var $prev = pageBuilder('&laquo;', active - 1);
            $pagination.prepend($prev);
            if (active < 2)
                $prev.addClass('disabled');

            var $next = pageBuilder('&raquo;', active + 1);
            $pagination.append($next);
            if (active + 1 > total)
                $next.addClass('disabled');
        }

        $element.show();
    }

    function normalizeNumber(number) {
        return !jQuery.isNumeric(number) ? 0 : Math.abs(Math.floor(number));
    }



    // ALERT PLUGIN DEFINITION
    // =======================

    function Plugin(option) {
        var args = arguments;
        return this.each(function () {
            var $this = $(this)
            var data = $this.data('mb.pagination')

            if (!data) $this.data('mb.pagination', (data = new Pagination(this, option)))
            if (typeof option == 'string') data[option].call(data, Array.prototype.slice.call(args, 1, args.length))
        })
    }

    var old = $.fn.pagination

    $.fn.pagination = Plugin;
    $.fn.pagination.Constructor = Pagination;


    $.fn.pagination.noConflict = function () {
        $.fn.pagination = old
        return this
    }

    $(document).on("click", '.pagination [data-page-index]', function (e) {
        e.preventDefault();

        var $page = $(e.target).closest("[data-page-index]");

        $page.closest(".pagination")
            .parent()
            .pagination("setActive", $page.data("pageIndex"));
        
    });
}();