package shift.sextiarysector.asm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.Opcodes;

public class CauldronTransformer implements IClassTransformer, Opcodes {
    // 改変対象のクラスの完全修飾名です。
    // 後述でMinecraft.jar内の難読化されるファイルを対象とする場合の簡易な取得方法を紹介します。
    private static final String TARGET_CLASS_NAME = "net.minecraft.block.BlockCauldron";

    // クラスがロードされる際に呼び出されるメソッドです。
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        // FMLRelauncher.side() : Client/Server どちらか一方のを対象とする場合や、
        // 一つのMODで Client/Sever 両方に対応したMODで、この値を判定して処理を変える事ができます。
        // 今回は"CLIENT"と比較し、Client側のファイルを対象としている例です。
        // Client側専用のMODとして公開するのであれば、判定は必須ではありません。

        // name : 現在ロードされようとしているクラス名が格納されています。
        if (!transformedName.equals(TARGET_CLASS_NAME)) {
            // 処理対象外なので何もしない
            return basicClass;
        }

        try {
            // --------------------------------------------------------------
            // クラスファイル丸ごと差し替える場合
            // --------------------------------------------------------------
            return replaceClass(basicClass);

            // --------------------------------------------------------------
            // ASMを使用し、既存のクラスファイルに改変を施す場合。
            // --------------------------------------------------------------
            // return hookDoRenderLivingMethod(bytes);

        } catch (Exception e) {
            throw new RuntimeException("failed : TutorialTransformer loading", e);
        }
    }

    // 下記の想定で実装されています。
    // 対象クラスの bytes を ModifiedTargetClass.class ファイルに置き換える
    private byte[] replaceClass(byte[] bytes) throws IOException {
        ZipFile zf = null;
        InputStream zi = null;

        File f = null;

        try {

            if (SSCore.location.isDirectory()) {

                f = new File(SSCore.location, "./net/minecraft/block/BlockCauldron.class");

                if (f != null) {

                    zi = new FileInputStream(f);
                    int len = (int) f.length();
                    bytes = new byte[len];

                    // ヒープサイズを超えないように、ストリームからファイルを1024ずつ読み込んで bytes に格納する
                    int MAX_READ = 1024;
                    int readed = 0, readsize, ret;
                    while (readed < len) {
                        readsize = MAX_READ;
                        if (len - readed < MAX_READ) {
                            readsize = len - readed;
                        }
                        ret = zi.read(bytes, readed, readsize);
                        if (ret == -1) break;
                        readed += ret;
                    }
                }

            } else {

                zf = new ZipFile(SSCore.location);

                // 差し替え後のファイルです。coremodのjar内のパスを指定します。
                ZipEntry ze = zf.getEntry("BlockSSCauldron.class");

                if (ze != null) {
                    zi = zf.getInputStream(ze);
                    int len = (int) ze.getSize();
                    bytes = new byte[len];

                    // ヒープサイズを超えないように、ストリームからファイルを1024ずつ読み込んで bytes に格納する
                    int MAX_READ = 1024;
                    int readed = 0, readsize, ret;
                    while (readed < len) {
                        readsize = MAX_READ;
                        if (len - readed < MAX_READ) {
                            readsize = len - readed;
                        }
                        ret = zi.read(bytes, readed, readsize);
                        if (ret == -1) break;
                        readed += ret;
                    }
                }

            }

            return bytes;
        } finally {
            if (zi != null) {
                zi.close();
            }

            if (zf != null) {
                zf.close();
            }
        }
    }
}
