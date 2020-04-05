var index = {
    init: function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save: function () {
        var data = {
            title: $('#title').val(),
            contents: $('#contents').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/board/write',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: function () {
                alert('글이 등록되었습니다.');
                window.location.href = '/api/board';
            },
            error: function (error) {
                alert(JSON.stringify(error));
            }
        });
    },
    update: function () {
        var boardIdx = $('#boardIdx').val();

        var data = {
            boardIdx: boardIdx,
            title: $('#title').val(),
            contents : $('#contents').val()
        };

        $.ajax({
            type: 'PUT',
            url: '/api/board/' + boardIdx,
            dataType: 'json',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            success: function () {
                alert('글이 수정되었습니다.');
                window.location.href = '/api/board';
            },
            error: function (error) {
                alert(JSON.stringify(error));
            }
        });
    },
    delete : function () {
        var boardIdx = $('#boardIdx').val();

        $.ajax({
            type : 'DELETE',
            url : '/api/board/'+boardIdx,
            dataType:'json',
            contentType:'application/json;charset=utf-8',
        }).done(function () {
            alert("글이 삭제되었습니다.");
            window.location.href = '/api/board';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

index.init();

