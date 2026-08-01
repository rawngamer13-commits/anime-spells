@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class ClientEventHandler {
    
    @SubscribeEvent
    public static void onRenderSky(RenderSkyEvent.Pre event) {
        // Aquí irá la lógica para cambiar el cielo a rojo carmesí
        // cuando el hechizo Doom esté activo
    }
}
