/**
 * Created by weiyiqing on 17/1/22.
 */
var ZteCore = { initial: function (rel) {
        rel = rel || "";
        var el = this.findScript();
        var p = "";
        if (el)
        {
            p = this.getPath(el.src);
        }
        this.activeScript(p + rel, el);
    }, findScript: function () {
        var scripts = document.getElementsByTagName("script");
        if (!scripts)
        {
            return scripts;
        }

        for (var i=0; i<scripts.length; i++)
        {
            var name = this.getName(scripts[i].src);
            if (name && this.isMatch(name))
            {
                return scripts[i];
            }
        }

        return null;
    }, getPath: function (path) {
        var parent = "";
        if (path)
        {
            var array = path.split("/");
            if (array)
            {
                for (var i=0; i<array.length - 1; i++)
                {
                    parent += array[i] + "/";
                }
            }
        }

        return parent;
    }, getName: function (path) {
        if (path)
        {
            var array = path.split("/");
            if (array && array.length > 0)
            {
                return array[array.length - 1];
            }
        }

        return null;
    }, isMatch: function (name) {
        if ("core.js" == name)
        {
            return true;
        }

        return false;
    }, activeScript: function (rel, el) {
        if (!rel || !el)
        {
            return;
        }

        var ga = document.createElement('script');
        ga.type = 'text/javascript';
        ga.async = true;
        ga.src = rel + "cordova.js";
        el.parentNode.insertBefore(ga, el);
    }
}

ZteCore.initial("../../");




