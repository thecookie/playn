/**
 * Copyright 2010 The PlayN Authors
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
package playn.java;

import playn.core.Asserts;

import static playn.core.PlayN.graphics;
import playn.core.CanvasSurface;
import playn.core.Surface;
import playn.core.SurfaceLayer;

class JavaSurfaceLayer extends JavaLayer implements SurfaceLayer {

  private JavaImage img;
  private Surface surface;

  JavaSurfaceLayer(int width, int height) {
    super();
    img = (JavaImage) graphics().createImage(width, height);
    surface = new CanvasSurface(img.canvas());
  }

  @Override
  public void destroy() {
    super.destroy();
    surface = null;
    img = null;
  }

  @Override
  public Surface surface() {
    return surface;
  }

  @Override
  void paint(JavaCanvas canvas) {
    if (!visible()) return;

    canvas.save();
    transform(canvas);
    canvas.setAlpha(canvas.alpha() * alpha);
    canvas.drawImage(img, 0, 0);
    canvas.restore();
  }

  @Override
  public float width() {
    Asserts.checkNotNull(surface, "Surface must not be null");
    return surface.width();
  }

  @Override
  public float height() {
    Asserts.checkNotNull(surface, "Surface must not be null");
    return surface.height();
  }

  @Override
  public float scaledWidth() {
    return transform().scaleX() * width();
  }

  @Override
  public float scaledHeight() {
    return transform().scaleY() * height();
  }
}
