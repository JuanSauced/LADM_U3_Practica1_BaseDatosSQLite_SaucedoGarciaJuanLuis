package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.ui.gallery

import android.R
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
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var mos= Propietario(requireContext())
        var arr= ArrayList<String>()


        binding.btnBuscarPropietario.setOnClickListener {
            if(binding.etNombre.text.toString()==""){
                if(binding.etTelefono.text.toString()==""){
                    if (binding.etEdadM.text.toString()==""){
                        Toast.makeText(requireContext(),"Ingrese un parametro de busqueda",Toast.LENGTH_LONG)
                            .show()
                    }else{
                        arr=mos.mostrarEdad(binding.etEdadM.text.toString(),binding.etEdadMa.text.toString())
                        mostrarLista(arr)
                    }
                }else{
                    arr=mos.mostrarTelefono(binding.etTelefono.text.toString())
                    mostrarLista(arr)
                }
            }else{
                arr=mos.mostrarNombreLike(binding.etNombre.text.toString())
                mostrarLista(arr)
            }
            binding.etEdadM.setText("")
            binding.etTelefono.setText("")
            binding.etNombre.setText("")
            binding.etEdadMa.setText("")
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun mostrarLista(arr :ArrayList<String>){
        binding.lista.adapter=
            ArrayAdapter<String>(requireContext(), R.layout.simple_list_item_1,arr)
    }
}