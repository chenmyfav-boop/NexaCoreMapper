package com.nexacore.mapper

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.*
import android.graphics.drawable.GradientDrawable

class MainActivity : Activity() {
    private val bg = Color.rgb(12, 18, 16)
    private val card = Color.rgb(23, 31, 29)
    private val green = Color.rgb(24, 217, 160)
    private lateinit var root: LinearLayout
    private lateinit var content: LinearLayout
    private var page = "Mulai Game"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg
        render()
    }

    private fun panel(): GradientDrawable = GradientDrawable().apply {
        setColor(card); cornerRadius = dp(18f); setStroke(dp(1), Color.rgb(34, 46, 42))
    }
    private fun dp(v: Float) = (v * resources.displayMetrics.density).toInt()
    private fun text(s: String, size: Float, color: Int = Color.WHITE, bold: Boolean = false): TextView =
        TextView(this).apply {
            text = s; textSize = size; setTextColor(color)
            if (bold) setTypeface(null, Typeface.BOLD)
        }
    private fun button(s: String, action: () -> Unit): TextView = text(s, 15f, Color.WHITE, true).apply {
        gravity = Gravity.CENTER; setPadding(dp(14f), dp(13f), dp(14f), dp(13f))
        background = GradientDrawable().apply { setColor(green); cornerRadius = dp(12f) }
        setOnClickListener { action() }
    }
    private fun card(title: String, subtitle: String = "", action: (() -> Unit)? = null): LinearLayout {
        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL; setPadding(dp(16f), dp(16f), dp(16f), dp(16f))
            background = panel()
        }
        box.addView(text(title, 17f, Color.WHITE, true))
        if (subtitle.isNotBlank()) {
            val sub = text(subtitle, 13f, Color.rgb(155, 166, 161))
            sub.setPadding(0, dp(7f), 0, 0); box.addView(sub)
        }
        if (action != null) box.setOnClickListener { action() }
        return box
    }
    private fun render() {
        root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setBackgroundColor(bg) }
        val header = LinearLayout(this).apply {
            gravity = Gravity.CENTER_VERTICAL; setPadding(dp(20f), dp(16f), dp(20f), dp(12f))
        }
        header.addView(text("◉  NEXACORE", 20f, green, true), LinearLayout.LayoutParams(0, -2, 1f))
        header.addView(text("DEV  •  0.1.0", 12f, Color.GRAY))
        root.addView(header)
        val scroll = ScrollView(this)
        content = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(16f), dp(8f), dp(16f), dp(18f)) }
        scroll.addView(content); root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        when(page) {
            "Mulai Game" -> home()
            "Akun Saya" -> account()
            else -> settings()
        }
        val nav = LinearLayout(this).apply {
            setPadding(dp(8f), dp(8f), dp(8f), dp(8f)); background = panel()
        }
        listOf("Mulai Game", "Akun Saya", "Pengaturan").forEach { label ->
            val item = text(label, 12f, if(page == label) green else Color.LTGRAY, page == label).apply {
                gravity = Gravity.CENTER; setPadding(dp(5f), dp(12f), dp(5f), dp(12f))
                setOnClickListener { page = label; render() }
            }
            nav.addView(item, LinearLayout.LayoutParams(0, -2, 1f))
        }
        root.addView(nav)
        setContentView(root)
    }
    private fun addGap() { content.addView(View(this), LinearLayout.LayoutParams(1, dp(12f))) }
    private fun home() {
        content.addView(text("Dashboard", 23f, Color.WHITE, true)); addGap()
        val status = card("Koneksi perangkat", "Mouse: Belum tersambung     Keyboard: Belum tersambung")
        content.addView(status); addGap()
        content.addView(button("+  Tambah Game") { Toast.makeText(this, "Pemilih game akan ditambahkan pada tahap berikutnya", Toast.LENGTH_SHORT).show() })
        addGap()
        content.addView(card("Free Fire", "Profil game • Belum dikonfigurasi") { page = "Pengaturan"; render() })
        addGap()
        content.addView(card("Edit Keymap", "Editor kontrol • Tahap implementasi berikutnya") { page = "Pengaturan"; render() })
        addGap()
        content.addView(card("Profil Keymap", "Simpan dan pilih preset kontrol"))
        addGap()
        content.addView(card("Pengaturan Mouse", "Sensitivitas dan input mouse"))
        addGap()
        content.addView(card("Macro", "Belum tersedia di prototype"))
    }
    private fun account() {
        content.addView(text("Akun Saya", 23f, Color.WHITE, true)); addGap()
        content.addView(card("NexaCore Mapper", "Versi 0.1.0 • Semua fitur aplikasi yang dibuat sendiri direncanakan gratis"))
        addGap()
        content.addView(card("Informasi perangkat", "Android ${android.os.Build.VERSION.RELEASE}\n${android.os.Build.MODEL}\n${android.os.Build.SUPPORTED_ABIS.firstOrNull() ?: "Unknown"}"))
    }
    private fun settings() {
        content.addView(text("Pengaturan", 23f, Color.WHITE, true)); addGap()
        content.addView(card("Pemantauan layanan", "Service: belum aktif\nStatus: Prototype UI"))
        addGap()
        content.addView(card("Wireless Debugging", "Pairing dan koneksi belum diimplementasikan"))
        addGap()
        content.addView(card("Shizuku", "Deteksi service dan izin belum diimplementasikan"))
        addGap()
        content.addView(card("Pengaturan tambahan", "Tema • Animasi • Diagnostik"))
        addGap()
        content.addView(card("Pengaturan input", "Keyboard • Mouse • Sensitivitas"))
        addGap()
        content.addView(button("Lihat status perangkat") {
            Toast.makeText(this, "Prototype: belum melakukan pemeriksaan service", Toast.LENGTH_SHORT).show()
        })
    }
}
