package org.wanggz.engine;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.simpleEL.eval.DefaultExpressEvalService;

public class SimpleELMain {

    public static void main(String args[]) {
        DefaultExpressEvalService service = new DefaultExpressEvalService();
        service.regsiterVariant(Integer.class, "a", "b");
        service.regsiterVariant(Long.class, "c");
        service.setAllowMultiStatement(true); //设置支持多行语句

        Map<String, Object> ctx = new HashMap<String, Object>();
        ctx.put("a", 4);
        ctx.put("b", 3);
        ctx.put("c", 7890L);

        String express = "if (@b > @a) { return @a + @b; } else {return @c; }";
        Object result = service.eval(ctx, express);
        System.out.println("==============================" + result);
    }
}
