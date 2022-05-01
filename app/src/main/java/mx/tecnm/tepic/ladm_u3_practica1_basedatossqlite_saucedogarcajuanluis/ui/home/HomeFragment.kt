package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.Propietario
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.databinding.FragmentHomeBinding
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var mos=Propietario(requireContext())
        var arr= ArrayList<String>()
        arr=mos.mostrarLista()
        mostrarLista(arr)

        binding.btnAgregarPropietario.setOnClickListener {
            if (binding.etCurp.text.toString().equals("") ||
                binding.etEdad.text.toString().equals("") ||
                binding.etNombre.text.toString().equals("") ||
                binding.etTelefono.text.toString().equals("")){
                Toast.makeText(requireContext(),"Rellene los campos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            var prop=Propietario(requireContext())
            prop.curp=binding.etCurp.text.toString()
            prop.edad=binding.etEdad.text.toString().toInt()
            prop.telefono=binding.etTelefono.text.toString()
            prop.nombre=binding.etNombre.text.toString()
                binding.etCurp.setText("")
                binding.etEdad.setText("")
                binding.etNombre.setText("")
                binding.etTelefono.setText("")
            prop.insertar()
            arr=mos.mostrarLista()
            mostrarLista(arr)

        }




        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun mostrarLista(arr :ArrayList<String>){
        binding.lista.adapter=ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,arr)
    }
}