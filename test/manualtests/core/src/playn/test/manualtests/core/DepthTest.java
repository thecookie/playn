/**
 * Copyright 2011 The PlayN Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package playn.test.manualtests.core;

import static playn.core.PlayN.*;

import playn.core.CanvasLayer;
import playn.core.GroupLayer;

public class DepthTest extends ManualTest {
  @Override
  public String getName() {
    return "DepthTest";
  }

  @Override
  public String getDescription() {
    return "Tests that layers added with non-zero depth are inserted/rendered in proper order.";
  }

  @Override
  public void init() {
    GroupLayer rootLayer = graphics().rootLayer();

    CanvasLayer info = graphics().createCanvasLayer(250, 20);
    info.canvas().drawText(rootLayer.getClass().getName(), 0, 15);
    info.setTranslation(5, 5);
    rootLayer.add(info);

    int[] depths = { 0, -1, 1, 3, 2, -4, -3, 4, -2 };
    int[] fills = { 0xFF99CCFF, 0xFFFFFF33, 0xFF9933FF, 0xFF999999, 0xFFFF0033,
                    0xFF00CC00, 0xFFFF9900, 0xFF0066FF, 0x0FFCC6666 };
    int width = 200, height = 200;
    for (int ii = 0; ii < depths.length; ii++) {
      CanvasLayer layer = graphics().createCanvasLayer(width, height);
      int depth = depths[ii];
      layer.setDepth(depth);
      layer.setTranslation(225-50*depth, 125+25*depth);
      layer.canvas().setFillColor(fills[ii]);
      layer.canvas().fillRect(0, 0, width, height);
      layer.canvas().setFillColor(0xFF000000);
      layer.canvas().drawText(depth + "/" + ii, 5, 15);
      rootLayer.add(layer);
    }
  }
}
