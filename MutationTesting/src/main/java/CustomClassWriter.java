import org.objectweb.asm.*;

/**
 * Created by tolgacaner on 22/11/16.
 */
public class CustomClassWriter extends ClassWriter {


    public CustomClassWriter() {
        super(262144);
    }

    @Override
    public void visit(int var1, int var2, String var3, String var4, String var5, String[] var6) {
        super.visit(var1, var2, var3, var4, var5, var6);
    }

    @Override
    public MethodVisitor visitMethod(int i, final String s, String s1, String s2, String[] strings) {
        MethodVisitor mv = super.visitMethod(i, s, s1, s2, strings);
        if (s.equalsIgnoreCase("<init>") || s.equalsIgnoreCase("main"))
            return new MethodAdapter(mv); //not caring, generic
        else if (s.equalsIgnoreCase("Method1") || s.equalsIgnoreCase("Method2"))
            return new ArithmeticOperatorReplacer(mv,s);
        else if (s.equalsIgnoreCase("Method3"))
            return new RelationalOperatorReplacer(mv,s);
        else if (s.equalsIgnoreCase("Method4")) //
            return new ConditionalOperatorReplacer(mv,s);
        return new CustomMethodAdapter(mv,s);
    }
}
