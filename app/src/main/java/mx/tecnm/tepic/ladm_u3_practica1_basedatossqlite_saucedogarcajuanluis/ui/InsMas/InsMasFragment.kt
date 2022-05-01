package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.ui.InsMas

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.Mascota
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.Propietario
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.R
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.databinding.FragmentGalleryBinding
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.databinding.InsMasFragmentBinding

class InsMasFragment : Fragment() {

    lateinit var binding: InsMasFragmentBinding

    companion object {
        fun newInstance() = InsMasFragment()
    }

    private lateinit var viewModel: InsMasViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= InsMasFragmentBinding.inflate(inflater,container,false)
        val root: View = binding.root

        var prop= Propietario(requireContext())
        var mos= Mascota(requireContext())
        var arr= ArrayList<String>()
        arr=mos.mostrarLista()
        mostrarLista(arr)

        binding.btnBuscarProp.setOnClickListener {

            arr=prop.mostrarNombreLike(binding.etNombreProp.text.toString())
            mostrarLista(arr)
        }

        binding.lista.setOnItemClickListener { adapterView, view, i, l ->
            binding.getCurp.setText(prop.listaCurp.get(i))
        }

        binding.btnAgregarMascota.setOnClickListener {
            mos.nombre=binding.etNombre.text.toString()
            mos.raza=binding.etRaza.text.toString()
            mos.curp=binding.getCurp.text.toString()
            binding.etNombre.setText("")
            binding.etRaza.setText("")
            binding.etNombreProp.setText("")
            binding.getCurp.setText("Seleccione un propietario")
            mos.insertar()
            arr=mos.mostrarLista()
            mostrarLista(arr)
        }


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InsMasViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun mostrarLista(arr :ArrayList<String>){
        binding.lista.adapter=
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1,arr)
    }

}