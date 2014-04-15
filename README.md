handlebars.java-pagination
==========================

Handlebars.java pagination helper

This is Handlebars.java's(https://github.com/jknack/handlebars.java) Helper that makes pagination.

- PaginationHelper.java : Helper class
    <br/>Some external library were used. google guava,slf4j... If you want, It is easy to remove
    
- pagination.hbs : asset hbs
{{{#!html
// Hello World 출력 프로그램
<!--pagination param ==> page:{{param.currentPage}} totalPageCount:{{param.totlalPageCount}} countPerPageGroup:{{param.countPerPageGroup}}-->
{{#pagination this param.currentPage param.totlalPageCount param.countPerPageGroup}}
    <div class="paging_bootstrap">
        <ul class="pagination">
            {{#if canGoPrevious}}
            <li class="prev"><a href="#" onclick="goPage({{previousIdx}}); return false;"><i class="icon-double-angle-left"></i></a></li>
            {{else}}
            <li class="prev disabled"><a href="#"><i class="icon-double-angle-left"></i></a></li>
            {{/if}}

            {{#each pages}}
            <li {{#if isCurrent}}class="active"{{/if}}><a href="#" onclick="goPage({{page}}); return false;">{{page}}</a></li>
            {{/each}}

            {{#if canGoNext}}
            <li class="next"><a href="#" onclick="goPage({{nextIdx}}); return false;"><i class="icon-double-angle-right"></i></a></li>
            {{else}}
            <li class="next disabled"><a href="#"><i class="icon-double-angle-right"></i></a></li>
            {{/if}}
        </ul>
    </div>
{{/pagination}}
}}}


