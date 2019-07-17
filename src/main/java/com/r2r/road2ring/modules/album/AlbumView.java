package com.r2r.road2ring.modules.album;

import com.r2r.road2ring.modules.common.BaseView;
import com.r2r.road2ring.modules.media.MediaView;
import java.util.List;
import lombok.Data;

@Data
public class AlbumView extends BaseView{
  List<MediaView> medias;
}
