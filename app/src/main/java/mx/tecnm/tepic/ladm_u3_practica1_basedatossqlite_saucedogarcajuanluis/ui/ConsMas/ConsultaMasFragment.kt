package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.ui.ConsMas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.Mascota
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.R
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.databinding.ActEliMasFragmentBinding
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.databinding.ConsultaMasFragmentBinding

class ConsultaMasFragment : Fragment() {

    companion object {
        fun newInstance() = ConsultaMasFragment()
    }
    lateinit var binding:ConsultaMasFragmentBinding
    private lateinit var viewModel: ConsultaMasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= ConsultaMasFragmentBinding.inflate(inflater,container,false)
        var arr= ArrayList<String>()
        var mos=Mascota(requireContext())
        arr=mos.mostrarLista()
        mostrarLista(arr)

        val root: View = binding.root


        binding.btnBuscarMascota.setOnClickListener {
            if(binding.etNombre.text.toString()==""){
                if(binding.etPropietario.text.toString()==""){
                    if (binding.etRaza.text.toString()==""){
                        Toast.makeText(requireContext(),"Ingrese un parametro de busqueda", Toast.LENGTH_LONG)
                            .show()
                    }else{
                        arr=mos.mostrarRaza(binding.etRaza.text.toString())
                        mostrarLista(arr)
                    }
                }else{
                    arr=mos.mostrarPropietario(binding.etPropietario.text.toString())
                    mostrarLista(arr)
                }
            }else{
                arr=mos.mostrarNombre(binding.etNombre.text.toString())
                mostrarLista(arr)
            }
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConsultaMasViewModel::class.java)
        // TODO: Use the ViewModel
    }
    fun mostrarLista(arr :ArrayList<String>){
        binding.lista.adapter=
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1,arr)
    }

}