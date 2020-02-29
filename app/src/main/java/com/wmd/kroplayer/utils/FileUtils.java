package com.wmd.kroplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.provider.MediaStore;

import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * Author:  Edwardwmd
 * E-mail:  1732141816wmd @ gmail.com
 * Link:    https://github.com/Edwardwmd
 * Data:    2020/2/2418
 * Version: 1.0.0
 * Desc:
 */
public class FileUtils {
      /**
       * 删除文件，可以是文件或文件夹
       *
       * @param delFile 要删除的文件夹或文件名
       * @return 删除成功返回true，否则返回false
       */
      public static boolean delete(Context context, String delFile) {
            File file = new File(delFile);
            if (!file.exists()) {
                  AppUtils.showSnackbar((Activity) context, "删除文件失败:" + delFile + "不存在！", false);
                  return false;
            } else {
                  if (file.isFile())
                        return deleteSingleFile(context, delFile);
                  else
                        return deleteDirectory(context, delFile);
            }
      }

      /**
       * 删除单个文件
       *
       * @param filePath$Name 要删除的文件的文件名
       * @return 单个文件删除成功返回true，否则返回false
       */
      public static boolean deleteSingleFile(Context context, String filePath$Name) {
            File file = new File(filePath$Name);
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                  if (file.delete()) {
                        Logger.e("--Method--", "Copy_Delete.deleteSingleFile: 删除单个文件" + filePath$Name + "成功！");
                        return true;
                  } else {
                        AppUtils.showSnackbar((Activity) context, "删除单个文件" + filePath$Name + "失败！", false);
                        return false;
                  }
            } else {
                  AppUtils.showSnackbar((Activity) context, "删除单个文件失败：" + filePath$Name + "不存在！", false);
//                  Toast.makeText(getApplicationContext(), "删除单个文件失败：" + filePath$Name + "不存在！", Toast.LENGTH_SHORT).show();
                  return false;
            }
      }

      /**
       * 删除目录及目录下的文件
       *
       * @param filePath 要删除的目录的文件路径
       * @return 目录删除成功返回true，否则返回false
       */
      public static boolean deleteDirectory(Context context, String filePath) {
            // 如果dir不以文件分隔符结尾，自动添加文件分隔符
            if (!filePath.endsWith(File.separator))
                  filePath = filePath + File.separator;
            File dirFile = new File(filePath);
            // 如果dir对应的文件不存在，或者不是一个目录，则退出
            if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
                  AppUtils.showSnackbar((Activity) context, "删除目录失败：" + filePath + "不存在！", false);
                  return false;
            }
            boolean flag = true;
            // 删除文件夹中的所有文件包括子目录
            File[] files = dirFile.listFiles();
            for (File file : files) {
                  // 删除子文件
                  if (file.isFile()) {
                        flag = deleteSingleFile(context, file.getAbsolutePath());
                        if (!flag)
                              break;
                  }
                  // 删除子目录
                  else if (file.isDirectory()) {
                        flag = deleteDirectory(context, file
                                .getAbsolutePath());
                        if (!flag)
                              break;
                  }
            }

            if (!flag) {
                  AppUtils.showSnackbar((Activity) context, "删除目录失败！", false);
                  return false;
            }
            // 删除当前目录
            if (dirFile.delete()) {
                  Logger.e("--Method--", "Copy_Delete.deleteDirectory: 删除目录" + filePath + "成功！");
                  return true;
            } else {
                  AppUtils.showSnackbar((Activity) context, "删除目录：" + filePath + "失败！！", false);
                  return false;
            }

      }

      /**
       * 删除SD卡内本地视频并使用广播更新数据
       * @param context 上下文
       * @param loacalVideoPath 本地视频路径
       * @return
       */
      public static int deleteExternalVideoFile(Context context, String loacalVideoPath) {
            return context.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    MediaStore.Video.Media.DATA + "= \"" + loacalVideoPath + "\"",
                    null);
      }
}
